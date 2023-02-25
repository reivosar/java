package reivosar.common.util.reflect.type;

import java.lang.reflect.Constructor;

class ConstructorParameterTypesDescriptor extends AbstractParameterTypesDescriptor {
    
    ConstructorParameterTypesDescriptor(final Constructor<?> constructor) {
        super(constructor.getParameterTypes());
    }
}
