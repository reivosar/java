package reivosar.common.lang.reflect.type;

class ClassTypeDescriptor extends AbstractTypeDescriptor {

    ClassTypeDescriptor(final Class<?> clazz) {
        super(clazz.getName(), clazz);
    }
}
