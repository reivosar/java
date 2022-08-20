package reivosar.common.util.promise;

public class PromiseException extends RuntimeException {
	
	public PromiseException(Throwable error) {
		super(error);
	}
	
	public PromiseException(String message) {
		super(message);
	}

    public PromiseException(String message, Throwable cause) {
        super(message, cause);
    }
}
