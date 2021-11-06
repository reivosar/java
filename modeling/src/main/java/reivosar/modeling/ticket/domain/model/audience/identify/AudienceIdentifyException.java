package reivosar.modeling.ticket.domain.model.audience.identify;

public class AudienceIdentifyException extends RuntimeException {

    public AudienceIdentifyException() {
        super();
    }

    public AudienceIdentifyException(final String message) {
        super(message);
    }

    public AudienceIdentifyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AudienceIdentifyException(final Throwable cause) {
        super(cause);
    }

}
