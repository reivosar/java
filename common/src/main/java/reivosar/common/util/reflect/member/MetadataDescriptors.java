package reivosar.common.util.reflect.member;

/**
 * This interface represents a collection of metadata descriptors that are also class member descriptors, which
 * include methods and fields.
 *
 * @param <T> the type of the metadata descriptor that is also a class member descriptor.
 */
public interface MetadataDescriptors<T extends ClassMemberDescriptor & MetadataDescriptor>
        extends ClassMemberDescriptors<T> {
}