package reivosar.common.util.event;

/**
 * The priority level of an event, which determines the order in which it will be processed by the event loop.
 * <p>
 * Higher priority events will be processed before lower priority events.
 */
public enum EventPriority {
    
    /**
     * The highest priority level for an event.
     */
    MAX(Thread.MAX_PRIORITY),
    /**
     * The default priority level for an event.
     */
    NORMAL(Thread.NORM_PRIORITY),
    /**
     * The lowest priority level for an event.
     */
    MIN(Thread.MIN_PRIORITY);
    
    private final int priority;
    
    /**
     * Constructs a new {@code EventPriority} with the given priority level.
     *
     * @param priority the priority level of the event
     */
    EventPriority(final int priority) {
        this.priority = priority;
    }
    
    /**
     * Returns the numeric value of this priority level, which corresponds to the priority level of the event's
     * processing thread.
     *
     * @return the priority level as a number
     */
    public int asNumber() {
        return priority;
    }
}
