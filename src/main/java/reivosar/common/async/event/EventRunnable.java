package reivosar.common.async.event;

import reivosar.common.lang.function.LockableFunction;
import reivosar.common.data.model.Model;
import reivosar.common.async.promise.Promise;

import java.util.Optional;

class EventRunnable extends Model implements Runnable {
    
    private final EventStore eventStore;
    private final EventProcessor eventProcessor;
    private final EventDescriptor eventDescriptor;
    private final LockableFunction lockableFunction;
    
    private EventDescriptor holdEventDescriptor;
    private Throwable throwable;
    private STATUS status;
    
    private enum STATUS {
        PENDING, PREPARED, PROCESSING, PROCESSED,COMPLETED
    }
    
    EventRunnable(final EventStore eventStore,
                  final EventProcessor eventProcessor,
                  final EventDescriptor eventDescriptor) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
        this.eventDescriptor = eventDescriptor;
        this.lockableFunction = new LockableFunction();
        this.status = STATUS.PENDING;
    }
    
    boolean isPending() {
        return this.status == STATUS.PENDING;
    }
    
    boolean isPrepared() {
        return this.status == STATUS.PREPARED;
    }
    
    boolean isProcessed() {
        return this.status == STATUS.PROCESSED;
    }
    
    boolean isCompleted() {
        return this.status == STATUS.COMPLETED;
    }
    
    boolean isSameEvent(final EventDescriptor eventDescriptor) {
        return this.eventDescriptor.isSameEvent(eventDescriptor);
    }
    
    Optional<Throwable> getError() {
        return Optional.ofNullable(this.throwable);
    }
    
    @Override
    public void run() {
        if (lockableFunction.isNotLocked()) {
            lockableFunction.lockOn(() -> {
                final EventDescriptor processEventDescriptor = getHoldEventDescriptor();
                Promise.resolve(() -> beforeProcess(processEventDescriptor))
                        .then(result -> result && process(processEventDescriptor))
                        .then(result -> result && afterProcess(processEventDescriptor));
            });
        }
    }
    
    private EventDescriptor getHoldEventDescriptor() {
        final EventDescriptor processEventDescriptor = (this.holdEventDescriptor == null) ?
                DefaultEventDescriptor.publishedBy(this.eventDescriptor) :
                this.holdEventDescriptor;
        this.holdEventDescriptor = processEventDescriptor;
        return processEventDescriptor;
    }
    
    private boolean beforeProcess(final EventDescriptor processEventDescriptor) {
        if (isPending()) {
            final boolean result = this.eventStore.update(processEventDescriptor);
            changeStatus(STATUS.PREPARED);
            return result;
        }
        return isPrepared();
    }
    
    private boolean process(final EventDescriptor processEventDescriptor) {
        if (isPrepared()) {
            try {
                changeStatus(STATUS.PROCESSING);
                this.eventProcessor.process(processEventDescriptor.getEvent());
            } catch (Throwable throwable) {
                this.throwable = throwable;
            } finally {
                changeStatus(STATUS.PROCESSED);
            }
        }
        return isProcessed();
    }
    
    private boolean afterProcess(final EventDescriptor processEventDescriptor) {
        final EventDescriptor completedEventDescriptor = DefaultEventDescriptor.completedBy(processEventDescriptor);
        this.eventStore.update(completedEventDescriptor);
        this.holdEventDescriptor = completedEventDescriptor;
        changeStatus(STATUS.COMPLETED);
        return isCompleted();
    }
    
    private void changeStatus(final STATUS status) {
        this.status = status;
    }
}
