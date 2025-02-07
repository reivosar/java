package reivosar.common.async.event;

import reivosar.common.lang.reflect.ClassDescriptor;
import reivosar.common.lang.reflect.member.MethodDescriptor;
import reivosar.common.lang.reflect.member.MethodDescriptors;

import java.util.Collection;

class LoadedClassPathEventHandler<E extends Event> extends EventHandler<E> {

    private final Collection<ClassDescriptor> classDescriptors;

    LoadedClassPathEventHandler(final Collection<ClassDescriptor> classDescriptors) {
        this.classDescriptors = classDescriptors;
    }

    @Override
    public void handle(final E event) {
        for (final ClassDescriptor classDescriptor : classDescriptors) {
            classDescriptor.newInstance()
                    .ifPresent(o -> handleEvent(o, event, classDescriptor.getMethodDescriptors()));
        }
    }

    private void handleEvent(final Object eventHandlerInstance,
                             final E event,
                             final MethodDescriptors methodDescriptors) {
        methodDescriptors
                .getDescriptors().stream()
                .filter(md -> md.matchParameterType(event))
                .map(MethodDescriptor::getMethodAccessor)
                .forEach(accessor -> accessor.invokeMethod(eventHandlerInstance, event));
    }
}
