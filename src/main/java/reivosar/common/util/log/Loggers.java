package reivosar.common.util.log;

/**
 * This is the API for log output.
 */
public interface Loggers {
    
    /**
     * Log a message at the INFO level.
     *
     * @param logMessage the message output to log
     */
    void info(Object logMessage);
    
    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     *
     * @param format the format string
     * @param params a list of arguments
     */
    void info(Object format, Object... params);
    
    /**
     * Log an exception (throwable) at the INFO level with an
     * accompanying message.
     *
     * @param logMessage the message accompanying the exception
     * @param t          the exception (throwable) to log
     */
    void info(Object logMessage, Throwable t);
    
    /**
     * Log a message at the WARN level.
     *
     * @param logMessage the message output to log
     */
    void warn(Object logMessage);
    
    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     *
     * @param format the format string
     * @param params a list of arguments
     */
    void warn(Object format, Object... params);
    
    /**
     * Log an exception (throwable) at the WARN level with an
     * accompanying message.
     *
     * @param logMessage the message accompanying the exception
     * @param t          the exception (throwable) to log
     */
    void warn(Object logMessage, Throwable t);
    
    /**
     * Log a message at the TRACE level.
     *
     * @param logMessage the message output to log
     */
    void trace(Object logMessage);
    
    /**
     * Log a message at the TRACE level according to the specified format
     * and arguments.
     *
     * @param format the format string
     * @param params a list of arguments
     */
    void trace(Object format, Object... params);
    
    /**
     * Log an exception (throwable) at the TRACE level with an
     * accompanying message.
     *
     * @param logMessage the message accompanying the exception
     * @param t          the exception (throwable) to log
     */
    void trace(Object logMessage, Throwable t);
    
    /**
     * Log a message at the ERROR level.
     *
     * @param logMessage the message output to log
     */
    void error(Object logMessage);
    
    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     *
     * @param format the format string
     * @param params a list of arguments
     */
    void error(Object format, Object... params);
    
    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param logMessage the message accompanying the exception
     * @param t          the exception (throwable) to log
     */
    void error(Object logMessage, Throwable t);
    
    /**
     * Log a message at the DEBUG level.
     *
     * @param logMessage the message output to log
     */
    void debug(Object logMessage);
    
    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     *
     * @param format the format string
     * @param params a list of arguments
     */
    void debug(Object format, Object... params);
    
    /**
     * Log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     *
     * @param logMessage the message accompanying the exception
     * @param t          the exception (throwable) to log
     */
    void debug(Object logMessage, Throwable t);
    
    /**
     * Return a logger named according to the name parameter using the
     * statically bound {@code Loggers} instance.
     *
     * @param name The name of the logger.
     * @return {@code Loggers} instance
     */
    static Loggers getLoggers(String name) {
        return LoggersType.getDefaultLoggers(name);
    }
    
    /**
     * Return a logger named corresponding to the class passed as parameter,
     * using the statically {@code Loggers} instance.
     *
     * @param clazz the returned logger will be named after clazz
     * @return {@code Loggers} instance
     */
    static Loggers getLoggers(Class<?> clazz) {
        return LoggersType.getDefaultLoggers(clazz);
    }
}
