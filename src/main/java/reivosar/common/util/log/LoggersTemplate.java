package reivosar.common.util.log;

abstract class LoggersTemplate implements Loggers {

	@Override
	public void info(Object msg) {
		this.info(msg, new Object[0]);
	}

	@Override
	public void warn(Object msg) {
		this.warn(msg, new Object[0]);
	}

	@Override
	public void trace(Object msg) {
		this.trace(msg, new Object[0]);
	}

	@Override
	public void error(Object msg) {
		this.error(msg, new Object[0]);
	}

	@Override
	public void debug(Object msg) {
		this.debug(msg, new Object[0]);
	}
}
