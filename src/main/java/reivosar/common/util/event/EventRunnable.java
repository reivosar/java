package reivosar.common.util.event;

import reivosar.common.util.model.Model;

public class EventRunnable extends Model implements Runnable {
    
    private final EventStore eventStore;
    private final EventProcessor eventProcessor;
    private final EventDescriptor eventDescriptor;
    
    private EventDescriptor holdEventDescriptor;
    private STATUS status;
    
    private enum STATUS {
        PENDING, BEFORE_PROCESS_COMPLETED, PROCESSING, COMPLETED
    }
    
    EventRunnable(final EventStore eventStore,
                  final EventProcessor eventProcessor,
                  final EventDescriptor eventDescriptor) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
        this.eventDescriptor = eventDescriptor;
        this.status = STATUS.PENDING;
    }
    
    boolean isPending() {
        return status == STATUS.PENDING;
    }
    
    boolean isBeforeProcessCompleted() {
        return status == STATUS.BEFORE_PROCESS_COMPLETED;
    }
    
    boolean isCompleted() {
        return status == STATUS.COMPLETED;
    }
    
    boolean sameEventAs(final EventDescriptor eventDescriptor) {
        return this.eventDescriptor.getEventDescriptorIdentify().equals(eventDescriptor.getEventDescriptorIdentify());
    }
    
    @Override
    public void run() {
        final EventDescriptor processEventDescriptor = getHoldEventDescriptor();
        if (beforeProcess(processEventDescriptor)) {
            try {
                process(processEventDescriptor);
            } finally {
                afterProcess(processEventDescriptor);
            }
        }
    }
    
    private EventDescriptor getHoldEventDescriptor() {
        final EventDescriptor processEventDescriptor = (holdEventDescriptor == null) ?
                DefaultEventDescriptor.publishedBy(eventDescriptor) :
                holdEventDescriptor;
        holdEventDescriptor = processEventDescriptor;
        return processEventDescriptor;
    }
    
    private boolean beforeProcess(final EventDescriptor processEventDescriptor) {
        if (!isPending()) {
            return true;
        }
        final boolean result = eventStore.update(processEventDescriptor);
        if (result) {
            status = STATUS.BEFORE_PROCESS_COMPLETED;
        }
        return result;
    }
    
    private void process(final EventDescriptor processEventDescriptor) {
        if (isBeforeProcessCompleted()) {
            status = STATUS.PROCESSING;
            eventProcessor.process(processEventDescriptor.getEvent());
        }
    }
    
    private void afterProcess(final EventDescriptor processEventDescriptor) {
        final EventDescriptor completedEventDescriptor = DefaultEventDescriptor.completedBy(processEventDescriptor);
        eventStore.update(completedEventDescriptor);
        holdEventDescriptor = completedEventDescriptor;
        status = STATUS.COMPLETED;
    }
}
