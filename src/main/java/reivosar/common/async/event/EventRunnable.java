package reivosar.common.async.event;

import reivosar.common.async.promise.Promise;
import reivosar.common.data.model.Model;
import reivosar.common.lang.function.LockableFunction;

import java.util.Optional;

class EventRunnable<E extends Event> extends Model implements Runnable {

    private final EventStore<E> eventStore;
    private final EventDescriptor<E> eventDescriptor;
    private final LockableFunction lockableFunction;

    private EventDescriptor<E> holdEventDescriptor;
    private Throwable throwable;
    private STATUS status;
    
    private enum STATUS {
        PENDING, PREPARED, PROCESSING, PROCESSED,COMPLETED
    }

    EventRunnable(final EventStore<E> eventStore,
                  final EventDescriptor<E> eventDescriptor) {
        this.eventStore = eventStore;
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

    boolean isSameEvent(final EventDescriptor<E> eventDescriptor) {
        return this.eventDescriptor.isSameEvent(eventDescriptor);
    }

    Optional<Throwable> getError() {
        return Optional.ofNullable(this.throwable);
    }

    @Override
    public void run() {
        if (lockableFunction.isNotLocked()) {
            lockableFunction.lockOn(() -> {
                final EventDescriptor<E> processEventDescriptor = getHoldEventDescriptor();
                Promise.resolve(() -> beforeProcess(processEventDescriptor))
                        .then(result -> result && process(processEventDescriptor))
                        .then(result -> result && afterProcess(processEventDescriptor));
            });
        }
    }

    private EventDescriptor<E> getHoldEventDescriptor() {
        final EventDescriptor<E> processEventDescriptor = (this.holdEventDescriptor == null) ?
                DefaultEventDescriptor.publishedBy(this.eventDescriptor) :
                this.holdEventDescriptor;
        this.holdEventDescriptor = processEventDescriptor;
        return processEventDescriptor;
    }

    private boolean beforeProcess(final EventDescriptor<E> processEventDescriptor) {
        if (isPending()) {
            final boolean result = this.eventStore.applyEventResult(processEventDescriptor);
            changeStatus(STATUS.PREPARED);
            return result;
        }
        return isPrepared();
    }

    private boolean process(final EventDescriptor<E> processEventDescriptor) {
        if (isPrepared()) {
            try {
                changeStatus(STATUS.PROCESSING);
                final E event = processEventDescriptor.getEvent();
                final EventHandler<E> eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
                eventHandler.handle(event);
            } catch (Throwable throwable) {
                this.throwable = throwable;
            } finally {
                changeStatus(STATUS.PROCESSED);
            }
        }
        return isProcessed();
    }

    private boolean afterProcess(final EventDescriptor<E> processEventDescriptor) {
        final EventDescriptor<E> completedEventDescriptor = DefaultEventDescriptor.completedBy(processEventDescriptor);
        this.eventStore.applyEventResult(completedEventDescriptor);
        this.holdEventDescriptor = completedEventDescriptor;
        changeStatus(STATUS.COMPLETED);
        return isCompleted();
    }
    
    private void changeStatus(final STATUS status) {
        this.status = status;
    }
}
