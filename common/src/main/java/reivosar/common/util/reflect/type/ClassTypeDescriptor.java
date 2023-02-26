package reivosar.common.util.reflect.type;

class ClassTypeDescriptor extends AbstractTypeDescriptor implements TypeDescriptor {
    
    ClassTypeDescriptor(final Class<?> clazz) {
        super(clazz.getName(), clazz);
    }
}
