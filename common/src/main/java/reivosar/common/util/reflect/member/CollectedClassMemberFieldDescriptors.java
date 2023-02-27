package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

class CollectedClassMemberFieldDescriptors extends CollectedMetadataDescriptors<FieldDescriptor, FieldDescriptors>
        implements FieldDescriptors {
    
    protected CollectedClassMemberFieldDescriptors(final Field[] fields) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(fields, new Field[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(FieldDescriptorFactory::create)
                .toList());
    }
    
    @Override
    public Collection<FieldDescriptor> getFieldDescriptors() {
        return getDescriptors();
    }
}
