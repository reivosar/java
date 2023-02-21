package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

abstract class ClassMemberDescriptor extends Model {
    
    private final String name;
    private final AccessScope accessScope;
    private final MetadataAccessor metadataAccessor;
    
    protected ClassMemberDescriptor(
            final String name,
            final int modifier,
            final AccessibleObject accessibleObject)
    {
        this.name = name;
        this.accessScope = AccessScope.of(new ClassModifier(modifier));
        this.metadataAccessor = new MetadataAccessor(new ClassAccessibleObject(accessibleObject));
    }
    
    String getName() {
        return name;
    }
    
    boolean equalsByName(final String name) {
        return this.getName().equals(ObjectUtil.requireNonNull("name", name));
    }
    
    AccessScope getAccessScope() {
        return accessScope;
    }
    
    boolean hasAnnotatedMetadata(final Class<? extends Annotation> annotationClass) {
        return metadataAccessor.hasAnnotatedMetadata(annotationClass);
    }
}
