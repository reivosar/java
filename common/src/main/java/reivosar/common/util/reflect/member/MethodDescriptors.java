package reivosar.common.util.reflect.member;

import java.util.Collection;

/**
 * An interface that represents a descriptor for a method, providing access to the method's behavior and metadata.
 * This interface extends {@code ExecutableClassMemberDescriptors<MethodDescriptors>}.
 */
public interface MethodDescriptors extends ExecutableClassMemberDescriptors<MethodDescriptors> {
    
    /**
     * Returns a collection of method descriptors that are included in the object represented by this interface.
     *
     * @return a collection of method descriptors that are included in the object represented by this interface
     */
    Collection<MethodDescriptor> getMethodDescriptors();
}