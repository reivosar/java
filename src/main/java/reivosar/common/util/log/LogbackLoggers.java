package reivosar.common.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reivosar.common.lang.ObjectUtil;

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
	public void info(Object msg, Object... params) {
		this.logger.info(getMsgAsString(msg), params);
	}
    
    private String getMsgAsString(final Object msg) {
        return ObjectUtil.defaultIfNull(msg, "").toString();
    }
    
    @Override
	public void info(Object msg, Throwable t) {
		this.logger.info(getMsgAsString(msg), t);
	}

	@Override
	public void warn(Object msg, Object... params) {
		this.logger.warn(getMsgAsString(msg), params);
	}

	@Override
	public void warn(Object msg, Throwable t) {
		this.logger.warn(getMsgAsString(msg), t);
	}

	@Override
	public void trace(Object msg, Object... params) {
		this.logger.trace(getMsgAsString(msg), params);
	}

	@Override
	public void trace(Object msg, Throwable t) {
		this.logger.trace(getMsgAsString(msg), t);
	}

	@Override
	public void error(Object msg, Object... params) {
		this.logger.error(getMsgAsString(msg), params);
	}

	@Override
	public void error(Object msg, Throwable t) {
		this.logger.error(getMsgAsString(msg), t);
	}

	@Override
	public void debug(Object msg, Object... params) {
		this.logger.debug(getMsgAsString(msg), params);
	}

	@Override
	public void debug(Object msg, Throwable t) {
		this.logger.debug(getMsgAsString(msg), t);
	}
}
