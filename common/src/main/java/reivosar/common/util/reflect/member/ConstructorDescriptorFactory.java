package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Constructor;

/**
 * A factory for creating {@link ConstructorDescriptor} objects from {@link Constructor} objects.
 */
public class ConstructorDescriptorFactory {
    
    /**
     * Creates a new {@link ConstructorDescriptor} object for the specified Constructor.
     *
     * @param constructor the Constructor for which to create the descriptor
     * @return a new {@link ConstructorDescriptor} object for the specified Constructor
     * @throws NullPointerException if {@code Constructor} is {@code null}
     */
    public static ConstructorDescriptor create(final Constructor<?> constructor) {
        ObjectUtil.requireNonNull("constructor", constructor);
        return new ClassMemberConstructorDescriptor(constructor, true);
    }
}
