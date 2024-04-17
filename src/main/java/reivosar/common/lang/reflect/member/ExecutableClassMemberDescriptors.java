package reivosar.common.lang.reflect.member;

/**
 * An interface that represents a descriptor for executable class members, such as methods and constructors, providing
 * access to the behavior and metadata of these members.
 * This interface extends {@code MetadataDescriptors<T>}.
 *
 * @param <T> the type of the object that implements {@code ExecutableClassMemberDescriptor}.
 */
public interface ExecutableClassMemberDescriptors<T extends ExecutableClassMemberDescriptor>
        extends MetadataDescriptors<T> {
}