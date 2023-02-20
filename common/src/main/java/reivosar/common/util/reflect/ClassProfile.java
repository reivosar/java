package reivosar.common.util.reflect;

import org.apache.commons.lang3.ClassUtils;
import reivosar.common.util.lang.StringUtil;
import reivosar.common.util.model.Model;

class ClassProfile extends Model {
    
    private final Class<?> declaringClass;
    private final ClassModifier classModifier;
    
    ClassProfile(final Class<?> declaringClass) {
        this.declaringClass = declaringClass;
        this.classModifier = new ClassModifier(declaringClass.getModifiers());
    }
    
    Class<?> getDeclaringClass() {
        return declaringClass;
    }
    
    String getPackageName() {
        return StringUtil.defaultIfNullOrEmpty(ClassUtils.getPackageName(getDeclaringClass()), getDeclaringClass().getPackageName());
    }
    
    String getClassName() {
        return StringUtil.defaultIfNullOrEmpty(ClassUtils.getSimpleName(getDeclaringClass()), getDeclaringClass().getSimpleName());
    }
    
    AccessScope getAccessScope() {
        return AccessScope.of(classModifier);
    }
    
    boolean isStatic() {
        return classModifier.isStatic();
    }
    
    boolean isFinal() {
        return classModifier.isFinal();
    }
}