package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

class CollectedClassMemberFieldDescriptors
        extends CollectedMetadataDescriptors<FieldDescriptor, FieldDescriptors>
        implements FieldDescriptors {
    
    CollectedClassMemberFieldDescriptors(final Field[] fields) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(fields, new Field[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(FieldDescriptorFactory::create)
                .toList());
    }
}
