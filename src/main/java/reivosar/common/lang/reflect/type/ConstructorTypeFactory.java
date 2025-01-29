package reivosar.common.lang.reflect.type;

import java.lang.reflect.Constructor;

/**
 * A factory for creating parameter types descriptors for a given {@link Constructor} object.
 */
public class ConstructorTypeFactory {
    
    /**
     * Creates a new {@link ParameterTypesDescriptor} for the given constructor.
     *
     * @param constructor the constructor to append a parameter types descriptor for
     * @return a new parameter types descriptor for the given constructor
     */
    public static ParameterTypesDescriptor createParameterTypes(final Constructor<?> constructor) {
        return new ConstructorParameterTypesDescriptor(constructor);
    }
}
