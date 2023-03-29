package reivosar.common.util.function;

import reivosar.common.util.lang.ObjectUtil;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * A wrapper class around a `Lock` that provides a way to execute code within a locked section.
 */
public class LockableFunction {
    
    private final Lock delegate;
    private final AtomicBoolean locked;
    
    /**
     * Constructs a new `LockFunction` with a new `ReentrantLock`.
     */
    public LockableFunction() {
        this(new ReentrantLock());
    }
    
    /**
     * Constructs a new `LockFunction` with the given `Lock`.
     *
     * @param delegate the lock to use
     */
    public LockableFunction(final Lock delegate) {
        this.delegate = ObjectUtil.requireNonNull("Lock", delegate);
        this.locked = new AtomicBoolean(false);
    }
    
    /**
     * Returns true if the lock is currently locked, false otherwise.
     *
     * @return true if the lock is currently locked, false otherwise
     */
    public boolean isLocked() {
        return locked.get();
    }
    
    /**
     * Returns true if the lock is not currently locked, false otherwise.
     *
     * @return true if the lock is not currently locked, false otherwise
     */
    public boolean isNotLocked() {
        return !isLocked();
    }
    
    /**
     * Executes the given `Supplier` within a locked section and returns its result.
     *
     * @param supplier the `Supplier` to execute
     * @param <T>      the type of the return value of the `Supplier`
     * @return the result of executing the `Supplier`
     */
    public <T> T withLock(final Supplier<T> supplier) {
        ObjectUtil.requireNonNull("Supplier", supplier);
        synchronized (delegate) {
            try {
                locked.set(delegate.tryLock());
                return supplier.get();
            } finally {
                delegate.unlock();
                locked.set(false);
            }
        }
    }
    
    /**
     * Executes the given `VoidConsumer` within a locked section.
     *
     * @param consumer the `VoidConsumer` to execute
     */
    public void withLock(final VoidConsumer consumer) {
        ObjectUtil.requireNonNull("Consumer", consumer);
        synchronized (delegate) {
            try {
                locked.set(delegate.tryLock());
                consumer.accept();
            } finally {
                delegate.unlock();
                locked.set(false);
            }
        }
    }
}
