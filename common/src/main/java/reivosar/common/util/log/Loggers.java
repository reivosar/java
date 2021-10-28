package reivosar.common.util.log;

/**
 * This is the API for log output. 
 */
public interface Loggers {

	/**
	 * Log a message at the INFO level.
	 * 
	 * @param logMessage
	 */
	void info(String logMessage);

	/**
	 * Log a message at the INFO level according to the specified format
	 * and arguments.
	 * 
	 * @param format    the format string
	 * @param arguments a list of arguments
	 */
	void info(String format, Object... params);

	/**
	 * Log an exception (throwable) at the INFO level with an
	 * accompanying message.
	 *
	 * @param logMessage the message accompanying the exception
	 * @param t   the exception (throwable) to log	 * 
	 */
	void info(String logMessage, Throwable t);

	/**
	 * Log a message at the WARN level.
	 * 
	 * @param logMessage
	 */
	void warn(String logMessage);

	/**
	 * Log a message at the WARN level according to the specified format
	 * and arguments.
	 * 
	 * @param format    the format string
	 * @param arguments a list of arguments
	 */
	void warn(String format, Object... params);

	/**
	 * Log an exception (throwable) at the WARN level with an
	 * accompanying message.
	 *
	 * @param logMessage the message accompanying the exception
	 * @param t   the exception (throwable) to log	 * 
	 */
	void warn(String logMessage, Throwable t);

	/**
	 * Log a message at the TRACE level.
	 * 
	 * @param logMessage
	 */
	void trace(String logMessage);

	/**
	 * Log a message at the TRACE level according to the specified format
	 * and arguments.
	 * 
	 * @param format    the format string
	 * @param arguments a list of arguments
	 */
	void trace(String format, Object... params);

	/**
	 * Log an exception (throwable) at the TRACE level with an
	 * accompanying message.
	 *
	 * @param logMessage the message accompanying the exception
	 * @param t   the exception (throwable) to log	 * 
	 */
	void trace(String logMessage, Throwable t);

	/**
	 * Log a message at the ERROR level.
	 * 
	 * @param logMessage
	 */
	void error(String logMessage);

	/**
	 * Log a message at the ERROR level according to the specified format
	 * and arguments.
	 * 
	 * @param format    the format string
	 * @param arguments a list of arguments
	 */
	void error(String format, Object... params);

	/**
	 * Log an exception (throwable) at the ERROR level with an
	 * accompanying message.
	 *
	 * @param logMessage the message accompanying the exception
	 * @param t   the exception (throwable) to log	 * 
	 */
	void error(String logMessage, Throwable t);

	/**
	 * Log a message at the DEBUG level.
	 * 
	 * @param logMessage
	 */
	void debug(String logMessage);

	/**
	 * Log a message at the DEBUG level according to the specified format
	 * and arguments.
	 * 
	 * @param format    the format string
	 * @param arguments a list of arguments
	 */
	void debug(String format, Object... params);

	/**
	 * Log an exception (throwable) at the DEBUG level with an
	 * accompanying message.
	 *
	 * @param logMessage the message accompanying the exception
	 * @param t   the exception (throwable) to log	 * 
	 */
	void debug(String logMessage, Throwable t);

	/**
	 * Return a logger named according to the name parameter using the
	 * statically bound {@link Loggers} instance.
	 * 
	 * @param name
	 *            The name of the logger.
	 * @return {@link Loggers} instance
	 */
	public static Loggers getLoggers(String name) {
		return LoggersType.getDefaultLoggers(name);
	}

	/**
     * Return a logger named corresponding to the class passed as parameter,
     * using the statically {@link Loggers} instance.
	 * 
	 * @param clazz
	 *            the returned logger will be named after clazz 
	 * @return {@link Loggers} instance
	 */
	public static Loggers getLoggers(Class<?> clazz) {
		return LoggersType.getDefaultLoggers(clazz);
	}
}
