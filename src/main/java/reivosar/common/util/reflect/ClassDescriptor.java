package reivosar.common.util.reflect;

import reivosar.common.util.lang.ClassUtil;
import reivosar.common.util.reflect.member.*;

/**
 * This interface represents a class descriptor, which provides information about a Java class.
 */
public interface ClassDescriptor {
    
    /**
     * Returns the raw {@link Class} object associated with this descriptor.
     *
     * @return the raw {@code Class} object
     */
    Class<?> getRawClass();
    
    /**
     * Returns the package name of the class.
     *
     * @return the package name
     */
    default String getPackageName() {
        return ClassUtil.getPackageName(getRawClass());
    }
    
    /**
     * Returns the simple name of the class.
     *
     * @return the simple name
     */
    default String getSimpleName() {
        return ClassUtil.getSimpleName(getRawClass());
    }
    
    /**
     * Returns the fully qualified name of the class.
     *
     * @return the fully qualified name
     */
    default String getName() {
        return getRawClass().getName();
    }
    
    /**
     * Returns the class modifier of the class.
     *
     * @return the class modifier
     */
    default ClassModifier getClassModifier() {
        return new ClassModifier(getRawClass());
    }
    
    /**
     * Returns the field descriptors of the class.
     *
     * @return the field descriptors
     */
    default FieldDescriptors getFieldDescriptors() {
        return FieldDescriptorsFactory.createDescriptors(getRawClass());
    }
    
    /**
     * Returns the constructor descriptors of the class.
     *
     * @return the constructor descriptors
     */
    default ConstructorDescriptors getConstructorDescriptors() {
        return ConstructorDescriptorsFactory.createDescriptors(getRawClass());
    }
    
    /**
     * Returns a new instance of the class with the given parameters by invoking the constructor with matching parameter types.
     *
     * @param parameters the parameters to pass to the constructor
     * @return a new instance of the class with the given parameters
     */
    default Object newInstance(Object... parameters) {
        return getConstructorDescriptors()
                .filter(parameters)
                .getDescriptors().stream().findFirst()
                .map(ConstructorDescriptor::getConstructorAccessor)
                .map(ConstructorAccessor::newInstance)
                .orElseGet(null);
    }
    
    /**
     * Returns the method descriptors of the class.
     *
     * @return the method descriptors
     */
    default MethodDescriptors getMethodDescriptors() {
        return MethodDescriptorsFactory.createDescriptors(getRawClass());
    }
}