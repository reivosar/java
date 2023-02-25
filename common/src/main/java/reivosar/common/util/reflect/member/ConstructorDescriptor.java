package reivosar.common.util.reflect.member;

/**
 * A descriptor for a constructor, providing information and access to the constructor's metadata and behavior.
 */
public interface ConstructorDescriptor extends ClassMemberDescriptor, MetadataDescriptor, ExecutableClassMemberDescriptor {
    
    /**
     * Returns an instance of the `ConstructorAccessor` interface that provides access to the constructor's behavior at runtime.
     *
     * @return an instance of the `ConstructorAccessor` interface that can be used to create new instances of the constructor's class.
     */
    ConstructorAccessor getConstructorAccessor();
    
}
