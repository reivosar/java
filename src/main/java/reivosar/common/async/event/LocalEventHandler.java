package reivosar.common.async.event;

import reivosar.common.lang.reflect.ClassDescriptor;
import reivosar.common.lang.reflect.member.MethodDescriptor;
import reivosar.common.lang.reflect.member.MethodDescriptors;

import java.util.Collection;

class LocalEventHandler implements EventHandler {
    
    private final Collection<ClassDescriptor> classDescriptors;
    
    LocalEventHandler(final Collection<ClassDescriptor> classDescriptors) {
        this.classDescriptors = classDescriptors;
    }
    
    @Override
    public void handle(final Event event) {
        for (final ClassDescriptor classDescriptor : classDescriptors) {
            classDescriptor.newInstance().ifPresent(o -> handleEvent(o, event, classDescriptor.getMethodDescriptors()));
        }
    }
    
    private void handleEvent(
            final Object eventHandlerInstance,
            final Event event,
            final MethodDescriptors methodDescriptors) {
        methodDescriptors
                .getDescriptors().stream()
                .filter(md -> md.matchParameterType(event))
                .map(MethodDescriptor::getMethodAccessor)
                .forEach(accessor -> accessor.invokeMethod(eventHandlerInstance, event));
    }
}
