package reivosar.common.util.event;

import reivosar.common.util.reflect.ClassDescriptor;
import reivosar.common.util.reflect.member.ConstructorAccessor;
import reivosar.common.util.reflect.member.ConstructorDescriptor;
import reivosar.common.util.reflect.member.MethodDescriptor;
import reivosar.common.util.reflect.member.MethodDescriptors;

class DefaultLocalEventHandler extends LocalEventHandler {
    
    @Override
    void handle(final ClassDescriptor classDescriptor, final Event event) {
        final Object instance = getEventHandlerInstance(classDescriptor);
        handleEvent(instance, event, classDescriptor.getMethodDescriptors());
    }
    
    private Object getEventHandlerInstance(final ClassDescriptor classDescriptor) {
        return classDescriptor.getConstructorDescriptors()
                .filter() // Filter constructors with no arguments
                .getDescriptors().stream()
                .findFirst()
                .map(ConstructorDescriptor::getConstructorAccessor)
                .map(ConstructorAccessor::newInstance)
                .orElseThrow(() -> new EventHandlingException(
                        "The class that handles the event must have a default constructor with no arguments"));
    }
    
    private void handleEvent(
            final Object eventHandlerInstance,
            final Event event,
            final MethodDescriptors methodDescriptors) {
        methodDescriptors
                .getDescriptors().stream()
                .filter(md -> md.getParameterTypesDescriptor().isEqualParameterType(event))
                .findFirst()
                .map(MethodDescriptor::getMethodAccessor)
                .ifPresent(accessor -> accessor.invokeMethod(eventHandlerInstance, event));
    }
}
