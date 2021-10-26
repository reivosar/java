package reivosar.common.util.log;

public interface Loggers
{
	void info(String msg);

	void info(String msg, Object... params);

	void info(String msg, Throwable t);

	void warn(String msg);

	void warn(String msg, Object... params);

	void warn(String msg, Throwable t);

	void trace(String msg);

	void trace(String msg, Object... params);

	void trace(String msg, Throwable t);

	void error(String msg);

	void error(String msg, Object... params);

    void error(String msg, Throwable t);

    void debug(String msg);

	void debug(String msg, Object... params);

	void debug(String msg, Throwable t);

	public static Loggers getLoggers(String name) {
		return getLogbackLoggers(name);
	}

	public static Loggers getLoggers(Class<?> clazz) {
		return getLogbackLoggers(clazz);
	}

	public static Loggers getLogbackLoggers(String name) {
		return LoggersType.LOGBACK.getLoggers(name);
	}

	public static Loggers getLogbackLoggers(Class<?> clazz) {
		return LoggersType.LOGBACK.getLoggers(clazz);
	}
}
