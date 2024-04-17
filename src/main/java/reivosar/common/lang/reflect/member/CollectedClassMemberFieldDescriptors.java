package reivosar.common.lang.reflect.member;

import reivosar.common.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

class CollectedClassMemberFieldDescriptors
        extends CollectedMetadataDescriptors<FieldDescriptor>
        implements FieldDescriptors {
    
    CollectedClassMemberFieldDescriptors(final Field[] fields) {
        this(Arrays.stream(ObjectUtil.defaultIfNull(fields, new Field[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .filter(field -> !field.getName().startsWith("__")) // IntelliJ's coverage analysis measures
                .map(FieldDescriptorFactory::create)
                .toList());
    }
    
    private CollectedClassMemberFieldDescriptors(final Collection<FieldDescriptor> fieldDescriptors) {
        super(fieldDescriptors);
    }
    
    @Override
    public FieldDescriptors filter(final String name) {
        return new CollectedClassMemberFieldDescriptors(
                getDescriptors().stream()
                        .filter(d -> d.getName().equals(name))
                        .toList());
    }
    
    @Override
    public FieldDescriptors filter(final Class<? extends Annotation> annotationClass) {
        return new CollectedClassMemberFieldDescriptors(
                getDescriptors().stream()
                        .filter(d -> d.hasAnnotation(annotationClass))
                        .toList());
    }
}
