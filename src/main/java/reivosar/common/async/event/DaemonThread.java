package reivosar.common.async.event;

class DaemonThread extends Thread {
    
    public DaemonThread(final Runnable target) {
        super(target);
        setDaemon(true);
    }
    
}
