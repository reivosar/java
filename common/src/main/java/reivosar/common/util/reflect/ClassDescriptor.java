package reivosar.common.util.reflect;

import org.apache.commons.lang3.ClassUtils;
import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.util.Collection;

abstract class ClassDescriptor extends Model
        implements ClassProfile, ClassAccess, ClassMetadata {
    
    @Override
    public String getPackageName() {
        return ClassUtils.getPackageCanonicalName(getDeclaringClass());
    }
    
    @Override
    public String getClassName() {
        return ClassUtils.getSimpleName(getDeclaringClass());
    }
    
    @Override
    public final AccessScope getAccessScope() {
        return AccessScope.of(getClassModifier());
    }
    
    @Override
    public final boolean isStaticAccess() {
        return getClassModifier().isStatic();
    }
    
    protected abstract ClassModifier getClassModifier();
    
    @Override
    public final Collection<Annotation> getAnnotations() {
        return getClassAccessibleObject().getAnnotations();
    }
    
    @Override
    public final boolean hasAnnotation(Annotation annotation){
        return getClassAccessibleObject().hasAnnotation(annotation);
    }
    
    protected abstract ClassAccessibleObject getClassAccessibleObject();
}
