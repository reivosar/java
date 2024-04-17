package reivosar.common.lang.reflect.member;

import reivosar.common.lang.ClassUtil;
import reivosar.common.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

class CollectedClassMemberConstructorDescriptors
        extends CollectedExecutableClassMemberDescriptors<ConstructorDescriptor>
        implements ConstructorDescriptors {
    
    CollectedClassMemberConstructorDescriptors(final Constructor<?>[] constructors) {
        this(Arrays.stream(ObjectUtil.defaultIfNull(constructors, new Constructor[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(ConstructorDescriptorFactory::create)
                .toList());
    }
    
    private CollectedClassMemberConstructorDescriptors(final Collection<ConstructorDescriptor> constructorDescriptors) {
        super(constructorDescriptors);
    }
    
    @Override
    public ConstructorDescriptors filter(final Class<?>... parameterTypes) {
        return new CollectedClassMemberConstructorDescriptors(
                getDescriptors().stream()
                        .filter(d -> d.matchParameterType(parameterTypes))
                        .toList());
    }
    
    @Override
    public ConstructorDescriptors filter(final Object... parameters) {
        return filter(ClassUtil.toClass(parameters));
    }
    
    @Override
    public ConstructorDescriptors filter(final Class<? extends Annotation> annotationClass, final Class<?>... parameterTypes) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.hasAnnotation(annotationClass))
                        .toList(),
                parameterTypes);
    }
    
    @Override
    public ConstructorDescriptors filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return filter(annotationClass, ClassUtil.toClass(parameters));
    }
    
    private CollectedClassMemberConstructorDescriptors filter(final Collection<ConstructorDescriptor> collection,
                                                              final Class<?>[] parametersTypes) {
        Collection<ConstructorDescriptor> result = collection;
        if ((parametersTypes != null) && (parametersTypes.length > 0)) {
            result = result.stream()
                    .filter(d -> d.matchParameterType(parametersTypes))
                    .toList();
        }
        return new CollectedClassMemberConstructorDescriptors(result);
    }
}

