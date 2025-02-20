package reivosar.common.async.event;

import reivosar.common.lang.reflect.ClassDescriptor;
import reivosar.common.lang.reflect.ClassResources;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class LocalEventHandlerRegistry {
    
    private static final Collection<ClassDescriptor> CLASS_DESCRIPTORS;
    
    static {
        final Set<ClassDescriptor> classDescriptorSet = new HashSet<>();
        for (final ClassDescriptor descriptor : ClassResources.getAllClassDescriptors()) {
            if (descriptor.hasMethodWithAssignableParameterTypes(Event.class)) {
                classDescriptorSet.add(descriptor);
            }
        }
        CLASS_DESCRIPTORS = Collections.unmodifiableCollection(classDescriptorSet);
    }
    
    static Collection<ClassDescriptor> findByEvent(final Event event) {
        return CLASS_DESCRIPTORS.stream()
                .filter(classDescriptor -> classDescriptor.hasMethodWithMatchingParameterTypes(event.getClass()))
                .collect(Collectors.toList());
    }
}
