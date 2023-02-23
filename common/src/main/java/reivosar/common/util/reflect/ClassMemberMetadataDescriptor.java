package reivosar.common.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.util.Collection;

abstract class ClassMemberMetadataDescriptor<T extends Member & AnnotatedElement>
        extends ClassMemberDescriptor {
    
    private final MetadataAccessor metadataAccessor;
    
    protected ClassMemberMetadataDescriptor(final T member) {
        super(member);
        this.metadataAccessor = new MetadataAccessor(new ClassAccessibleObject(member));
    }
    
    public Collection<Annotation> getAnnotatedMetadata() {
        return metadataAccessor.getAnnotatedMetadata();
    }
    
    public boolean hasAnnotatedMetadata(final Class<? extends Annotation> annotationClass) {
        return metadataAccessor.hasAnnotatedMetadata(annotationClass);
    }
}
