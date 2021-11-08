package reivosar.common.promise;

public class PromiseException extends RuntimeException {
	
	public PromiseException(Throwable error) {
		super(error);
	}
	
	public PromiseException(String message) {
		super(message);
	}
}
