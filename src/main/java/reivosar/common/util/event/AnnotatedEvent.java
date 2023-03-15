package reivosar.common.util.event;

import java.lang.annotation.Annotation;

/**
 * AnnotatedEvent interface represents an event with an associated annotation class, group name and event name.
 */
public interface AnnotatedEvent extends Event {
    
    /**
     * Returns the class of the associated annotation.
     *
     * @return the annotation class
     */
    Class<? extends Annotation> getAnnotationClass();
    
    /**
     * Returns the group name of the event.
     *
     * @return the group name
     */
    String getGroupName();
    
    /**
     * Returns the name of the event.
     *
     * @return the event name
     */
    String getEventName();
}