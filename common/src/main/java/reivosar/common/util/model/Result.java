package reivosar.common.util.model;

import java.util.Optional;

public interface Result<T> {
	
	boolean success();
	
	boolean fail();

	Optional<T> result();
	
	Optional<Throwable> error();
}
