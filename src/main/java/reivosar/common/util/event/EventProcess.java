package reivosar.common.util.event;

import java.time.LocalDateTime;

public class EventProcess {
    
    private final EventProcessId eventProcessId;
    private final EventPriority eventPriority;
    private final DaemonThread daemonThread;
    private final LocalDateTime processEntryDate;
    
    EventProcess(final EventPriority eventPriority, final Runnable runnable) {
        this.eventProcessId = new EventProcessId();
        this.eventPriority = eventPriority;
        this.daemonThread = new DaemonThread(eventPriority, runnable);
        this.processEntryDate = LocalDateTime.now();
    }
    
    EventProcessId getProcessId() {
        return eventProcessId;
    }
    
    int getPriorityAsNumber() {
        return eventPriority.asNumber();
    }
    
    private ThreadState getTreadState() {
        return ThreadState.of(daemonThread.getState());
    }
    
    boolean isPending() {
        return getTreadState().isPending();
    }
    
    boolean isRunning() {
        return getTreadState().isRunning();
    }
    
    boolean isCompleted() {
        return getTreadState().isCompleted();
    }
    
    boolean isInActive() {
        return getTreadState().isInActive();
    }
    
    LocalDateTime getProcessEntryDate() {
        return processEntryDate;
    }
}
