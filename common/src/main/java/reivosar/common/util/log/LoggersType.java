package reivosar.common.util.log;

enum LoggersType
{
	LOGBACK() {
		@Override
		Loggers getLoggers(Class<?> clazz) {
			return new LogbackLoggers(clazz);
		}
		@Override
		Loggers getLoggers(String name) {
			return new LogbackLoggers(name);
		}
	}
	;
	abstract Loggers getLoggers(String name);

	abstract Loggers getLoggers(Class<?> clazz);
}
