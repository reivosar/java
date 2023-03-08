package reivosar.common.util.reflect.type;

import java.lang.reflect.Method;

class MethodParameterTypesDescriptor extends AbstractParameterTypesDescriptor {
    
    MethodParameterTypesDescriptor(final Method method) {
        super(method.getParameterTypes());
    }
}
