package reivosar.common.util.reflect;

import reivosar.common.util.lang.ClassUtil;
import reivosar.common.util.lang.StringUtil;
import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ClassProfile extends Model {
    
    private final Class<?> declaringClass;
    private final ClassModifier classModifier;
    private final MetadataAccessor metadataAccessor;
    
    ClassProfile(final Class<?> declaringClass) {
        this.declaringClass = declaringClass;
        this.classModifier = new ClassModifier(declaringClass);
        this.metadataAccessor = new MetadataAccessor(new ClassAccessibleObject(declaringClass));
    }
    
    Class<?> getDeclaringClass() {
        return declaringClass;
    }
    
    String getPackageName() {
        return StringUtil.defaultIfNullOrEmpty(ClassUtil.getPackageName(getDeclaringClass()), getDeclaringClass().getPackageName());
    }
    
    String getClassName() {
        return StringUtil.defaultIfNullOrEmpty(ClassUtil.getSimpleName(getDeclaringClass()), getDeclaringClass().getSimpleName());
    }
    
    AccessScope getAccessScope() {
        return AccessScope.of(classModifier);
    }
    
    Collection<Annotation> getAnnotatedMetadata() {
        return metadataAccessor.getAnnotatedMetadata();
    }
    
    boolean isStatic() {
        return classModifier.isStatic();
    }
    
    boolean isFinal() {
        return classModifier.isFinal();
    }
}