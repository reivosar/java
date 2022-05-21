package reivosar.common.util.event.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventListener
{
    /**
     * The unique identifier of the container for this listener.
     *
     * @return the unique identifier of the listener that will process the event.
     */
    String id();
}
