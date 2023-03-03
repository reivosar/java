package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ArrayUtil;
import reivosar.common.util.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

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
    public MethodDescriptors filter(final String name, final Object... parameters) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.getName().equals(name))
                        .toList(),
                parameters);
    }
    
    @Override
    public MethodDescriptors filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.hasAnnotation(annotationClass))
                        .toList(),
                parameters);
    }
    
    private CollectedClassMemberMethodDescriptors filter(final Collection<MethodDescriptor> collection, final Object[] parameters) {
        Collection<MethodDescriptor> result = collection;
        if (ArrayUtil.isNotEmpty(parameters)) {
            result = result.stream()
                    .filter(d -> d.getParameterTypesDescriptor().isEqualParameterType(parameters))
                    .toList();
        }
        return new CollectedClassMemberMethodDescriptors(result);
    }
}
