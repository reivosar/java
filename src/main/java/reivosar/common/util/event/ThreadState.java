package reivosar.common.util.event;

enum ThreadState {
    PENDING, RUNNING, COMPLETED, INACTIVE;
    
    static ThreadState of(final Thread.State state) {
        if (state == null) {
            return INACTIVE;
        }
        return switch (state) {
            case NEW -> PENDING;
            case RUNNABLE, BLOCKED, TIMED_WAITING, WAITING -> RUNNING;
            case TERMINATED -> COMPLETED;
        };
    }
    
    boolean isPending() {
        return this == PENDING;
    }
    
    boolean isRunning() {
        return this == RUNNING;
    }
    
    boolean isCompleted() {
        return this == COMPLETED;
    }
    
    boolean isInActive() {
        return this == INACTIVE;
    }
}
