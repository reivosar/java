package reivosar.common.util.reflect.member;

import java.util.Collection;

/**
 * An interface that represents a descriptor for constructors, providing access to the behavior and metadata of the constructors.
 * This interface extends {@code ExecutableClassMemberDescriptors<ConstructorDescriptors>}.
 */
public interface ConstructorDescriptors extends ExecutableClassMemberDescriptors<ConstructorDescriptors> {
    
    /**
     * Returns a collection of constructor descriptors that are included in the object represented by this interface.
     *
     * @return a collection of constructor descriptors that are included in the object represented by this interface
     */
    Collection<ConstructorDescriptor> getConstructorDescriptor();
}