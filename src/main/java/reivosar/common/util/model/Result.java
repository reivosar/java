package reivosar.common.util.model;

import java.util.Optional;

/**
 * The object that implements this interface contains a 
 * method that returns a boolean indicating the success
 * or failure of the processing, a processing result object, 
 * and error information.
 *  
 * @param <T> 
 */
public interface Result<T> {
	
	/**
	 * Returns whether the process was successful or not.
	 * 
	 * @return {@code true} If the process is successful.
	 */
	boolean success();
	
	/**
	 * Returns whether the process failed or not.
	 * 
	 * @return {@code true} If the process fails.
	 */
	boolean fail();

	/**
	 * Returns the processing result.This method is of 
	 * type Optional, and null values will not be returned.
	 * 
	 * @return the processing result
	 */
	Optional<T> result();
	
	/**
	 * Return an throwable object.This method is of 
	 * type Optional, and null values will not be returned.
	 * 
	 * @return throwable object
	 */
	Optional<Throwable> error();
}
