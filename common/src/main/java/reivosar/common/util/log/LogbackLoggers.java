package reivosar.common.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogbackLoggers extends LoggersTemplate
{
	private final Logger logger;

	public LogbackLoggers(String name) {
		this.logger = LoggerFactory.getLogger(name);
	}

	public LogbackLoggers(Class<?> clazz) {
		this.logger = LoggerFactory.getLogger(clazz);
	}

	@Override
	public void info(String msg, Object... params) {
		this.logger.info(msg, params);
	}

	@Override
	public void info(String msg, Throwable t) {
		this.logger.info(msg, t);
	}

	@Override
	public void warn(String msg, Object... params) {
		this.logger.warn(msg, params);
	}

	@Override
	public void warn(String msg, Throwable t) {
		this.logger.warn(msg, t);
	}

	@Override
	public void trace(String msg, Object... params) {
		this.logger.trace(msg, params);
	}

	@Override
	public void trace(String msg, Throwable t) {
		this.logger.trace(msg, t);
	}

	@Override
	public void error(String msg, Object... params) {
		this.logger.error(msg, params);
	}

	@Override
	public void error(String msg, Throwable t) {
		this.logger.error(msg, t);
	}

	@Override
	public void debug(String msg, Object... params) {
		this.logger.debug(msg, params);
	}

	@Override
	public void debug(String msg, Throwable t) {
		this.logger.debug(msg, t);
	}
}
