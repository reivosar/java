package reivosar.common.util.event;

class EventPipeline {
    
    private final EventStore eventStore;
    private final EventProcessor eventProcessor;
    
    EventPipeline(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
    }
    
    boolean beforeProcess(EventDescriptor eventDescriptor) {
        return eventStore.update(eventDescriptor);
    }
    
    void process(EventDescriptor eventDescriptor) {
        eventProcessor.process(eventDescriptor.getEvent());
    }
    
    boolean afterProcess(EventDescriptor eventDescriptor) {
        return eventStore.update(eventDescriptor);
    }
}
