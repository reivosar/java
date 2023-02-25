package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.reflect.member.FieldDescriptor;
import reivosar.common.util.reflect.member.FieldDescriptorFactory;

import java.lang.reflect.Field;
import java.util.Arrays;

class FieldDescriptors extends ClassMemberMetadataDescriptors<FieldDescriptor> {
    
    FieldDescriptors(final Field[] fields) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(fields, new Field[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(FieldDescriptorFactory::create)
                .toList());
    }
}