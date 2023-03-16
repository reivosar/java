package reivosar.common.util.event;

import reivosar.common.util.reflect.ClassDescriptor;
import reivosar.common.util.reflect.member.MethodDescriptor;
import reivosar.common.util.reflect.member.MethodDescriptors;

import java.util.Collection;

class LocalEventHandler {
    
    private final Collection<ClassDescriptor> classDescriptors;
    
    LocalEventHandler(final Collection<ClassDescriptor> classDescriptors) {
        this.classDescriptors = classDescriptors;
    }
    
    void handle(final Event event) {
        classDescriptors.forEach(classDescriptor -> classDescriptor.newInstance()
                .ifPresent(instance -> handleEvent(instance, event, classDescriptor.getMethodDescriptors())));
    }
    
    private void handleEvent(
            final Object eventHandlerInstance,
            final Event event,
            final MethodDescriptors methodDescriptors) {
        methodDescriptors
                .getDescriptors().stream()
                .filter(md -> md.getParameterTypesDescriptor().isEqualParameterType(event))
                .map(MethodDescriptor::getMethodAccessor)
                .forEach(accessor -> accessor.invokeMethod(eventHandlerInstance, event));
    }
}
