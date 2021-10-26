package reivosar.common.util.log;

abstract class LoggersTemplate implements Loggers {

	@Override
	public void info(String msg) {
		this.info(msg, new Object[0]);
	}

	@Override
	public void warn(String msg) {
		this.warn(msg, new Object[0]);
	}

	@Override
	public void trace(String msg) {
		this.trace(msg, new Object[0]);
	}

	@Override
	public void error(String msg) {
		this.error(msg, new Object[0]);
	}

	@Override
	public void debug(String msg) {
		this.debug(msg, new Object[0]);
	}
}
