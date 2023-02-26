package reivosar.common.util.reflect.type;

import java.lang.reflect.Method;

class MethodReturnTypeDescriptor extends AbstractTypeDescriptor implements ReturnTypeDescriptor {

    MethodReturnTypeDescriptor(final Method method) {
        super(method.getName(), method.getReturnType());
    }
}
