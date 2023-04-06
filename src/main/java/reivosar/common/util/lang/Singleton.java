package reivosar.common.util.lang;

import java.util.function.Supplier;

/**
 * A factory class that produces a singleton instance of a generic type T.
 *
 * @param <T> The type of the singleton instance to be produced.
 */
public class Singleton<T> {
    
    private final Supplier<T> instanceSupplier;
    private volatile T instance;
    
    /**
     * Constructs a new SingletonInstanceFactory with the specified instanceSupplier.
     *
     * @param instanceSupplier The supplier to be used for producing the singleton instance.
     */
    public Singleton(Supplier<T> instanceSupplier) {
        this.instanceSupplier = instanceSupplier;
    }
    
    /**
     * Gets the singleton instance. If the instance has not yet been created, it is created on the first call to this method.
     * Subsequent calls to this method will return the existing singleton instance.
     *
     * @return The singleton instance of type T.
     */
    public T getInstance() {
        T result = instance;
        if (result == null) {
            synchronized (this) {
                result = instance;
                if (result == null) {
                    instance = result = instanceSupplier.get();
                }
            }
        }
        return result;
    }
}
