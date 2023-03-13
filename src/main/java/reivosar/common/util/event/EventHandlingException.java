package reivosar.common.util.event;

/**
 * Exception class to indicate an error occurred during event handling.
 */
public class EventHandlingException extends RuntimeException {
    
    /**
     * Constructs a new `EventHandlingException` with the specified detail message.
     *
     * @param message the detail message
     */
    public EventHandlingException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new `EventHandlingException` with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public EventHandlingException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new `EventHandlingException` with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public EventHandlingException(Throwable cause) {
        super(cause);
    }
}

