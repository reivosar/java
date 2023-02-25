package reivosar.common.util.reflect.type;

import reivosar.common.util.model.Model;

class ClassTypeDescriptor extends Model implements TypeDescriptor {
    
    private final Class<?> clazz;
    
    ClassTypeDescriptor(final Class<?> clazz) {
        this.clazz = clazz;
    }
    
    @Override
    public String getName() {
        return clazz.getName();
    }
    
    @Override
    public Class<?> getRawType() {
        return clazz;
    }
}
