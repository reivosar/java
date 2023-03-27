package reivosar.common.util.function;

import reivosar.common.util.lang.ObjectUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * A wrapper class around a `Lock` that provides a way to execute code within a locked section.
 */
public class LockableFunction {
    
    private final Lock lock;
    
    /**
     * Constructs a new `LockFunction` with a new `ReentrantLock`.
     */
    public LockableFunction() {
        this(new ReentrantLock());
    }
    
    /**
     * Constructs a new `LockFunction` with the given `Lock`.
     *
     * @param lock the lock to use
     */
    public LockableFunction(final Lock lock) {
        this.lock = ObjectUtil.requireNonNull("Lock", lock);
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
        try {
            lock.lock();
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Executes the given `VoidConsumer` within a locked section.
     *
     * @param consumer the `VoidConsumer` to execute
     */
    public void withLock(final VoidConsumer consumer) {
        ObjectUtil.requireNonNull("Consumer", consumer);
        try {
            lock.lock();
            consumer.accept();
        } finally {
            lock.unlock();
        }
    }
}
