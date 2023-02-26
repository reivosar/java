package reivosar.common.util.reflect.type;

import reivosar.common.util.model.Model;

abstract class AbstractTypeDescriptor extends Model implements TypeDescriptor {
    
    private final String name;
    private final Class<?> typeClass;
    
    protected AbstractTypeDescriptor(final String name, final Class<?> typeClass) {
        this.name = name;
        this.typeClass = typeClass;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Class<?> getRawType() {
        return typeClass;
    }
}
