package reivosar.common.util.event;

import reivosar.common.util.reflect.ClassDescriptor;

abstract class LocalEventHandler {
    abstract void handle(final ClassDescriptor classDescriptor, final Event event);
}
