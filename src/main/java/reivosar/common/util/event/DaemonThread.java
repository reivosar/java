package reivosar.common.util.event;

class DaemonThread extends Thread {
    
    DaemonThread(final EventPriority concurrentPriority, final Runnable runnable) {
        super(runnable);
        this.setDaemon(true);
        this.setPriority(concurrentPriority.asNumber());
    }
}
