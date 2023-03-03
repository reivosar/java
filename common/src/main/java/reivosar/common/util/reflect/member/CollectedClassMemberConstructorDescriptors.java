package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ArrayUtil;
import reivosar.common.util.lang.ObjectUtil;

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
    public ConstructorDescriptors filter(final String name, final Object... parameters) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.getName().equals(name))
                        .toList(),
                parameters);
    }
    
    @Override
    public ConstructorDescriptors filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return filter(getDescriptors().stream()
                        .filter(d -> d.hasAnnotation(annotationClass))
                        .toList(),
                parameters);
    }
    
    private CollectedClassMemberConstructorDescriptors filter(final Collection<ConstructorDescriptor> collection, final Object[] parameters) {
        Collection<ConstructorDescriptor> result = collection;
        if (ArrayUtil.isNotEmpty(parameters)) {
            result = result.stream()
                    .filter(d -> d.getParameterTypesDescriptor().isEqualParameterType(parameters))
                    .toList();
        }
        return new CollectedClassMemberConstructorDescriptors(result);
    }
}

