package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ClassUtil;
import reivosar.common.util.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

class CollectedClassMemberMethodDescriptors
        extends CollectedExecutableClassMemberDescriptors<MethodDescriptor>
        implements MethodDescriptors {
    
    CollectedClassMemberMethodDescriptors(final Method[] methods) {
        this(Arrays.stream(ObjectUtil.defaultIfNull(methods, new Method[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(MethodDescriptorFactory::create)
                .toList());
    }
    
    private CollectedClassMemberMethodDescriptors(final Collection<MethodDescriptor> methodDescriptors) {
        super(methodDescriptors);
    }
    
    @Override
    public MethodDescriptors filter(final String name, final Class<?>... parameterTypes) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.getName().equals(name))
                        .toList(),
                parameterTypes);
    }
    
    @Override
    public MethodDescriptors filter(final String name, final Object... parameters) {
        return filter(name, ClassUtil.toClass(parameters));
    }
    
    @Override
    public MethodDescriptors filter(final Class<? extends Annotation> annotationClass, final Class<?>... parameterTypes) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.hasAnnotation(annotationClass))
                        .toList(),
                parameterTypes);
    }
    
    @Override
    public MethodDescriptors filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return filter(annotationClass, ClassUtil.toClass(parameters));
    }
    
    private CollectedClassMemberMethodDescriptors filter(final Collection<MethodDescriptor> collection, final Class<?>[] parameterTypes) {
        Collection<MethodDescriptor> result = collection;
        if ((parameterTypes != null) && (parameterTypes.length > 0)) {
            result = result.stream()
                    .filter(d -> d.matchParameterType(parameterTypes))
                    .toList();
        }
        return new CollectedClassMemberMethodDescriptors(result);
    }
}
