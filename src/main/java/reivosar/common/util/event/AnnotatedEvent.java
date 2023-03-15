package reivosar.common.util.event;

import java.lang.annotation.Annotation;

public interface AnnotatedEvent extends Event {
    
    Class<? extends Annotation> getAnnotationClass();
    
    String getGroupName();
    
    String getTopicName();
}
