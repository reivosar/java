package reivosar.common.lang.reflect;

/**
 * Factory class for creating instances of {@link ClassDescriptor}.
 */
public class ClassDescriptorFactory {
    
    /**
     * Creates a new instance of {@link ClassDescriptor} for the given class.
     *
     * @param aClass the class to append a descriptor for.
     * @return a new instance of {@link ClassDescriptor} for the given class.
     */
    public static ClassDescriptor create(final Class<?> aClass) {
        return new DefaultClassDescriptor(aClass);
    }
}
