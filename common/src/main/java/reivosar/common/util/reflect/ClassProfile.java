package reivosar.common.util.reflect;

import org.apache.commons.lang3.ClassUtils;
import reivosar.common.util.model.Model;

class ClassProfile extends Model {
    
    private final Class<?> declaringClass;
    
    ClassProfile(final Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }
    
    public String getPackageName() {
        return ClassUtils.getPackageCanonicalName(declaringClass);
    }
    
    public String getClassName() {
        return ClassUtils.getSimpleName(declaringClass);
    }
}