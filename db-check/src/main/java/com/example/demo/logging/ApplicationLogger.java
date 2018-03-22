package com.example.demo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLogger {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void logInfo(String msg) {
		logger.info(msg);
	}
	public void logError(String msg,Throwable t) {
		logger.error(msg,t);
	}
	public void logDebug(String msg) {
		logger.debug(msg);
	}
	public void logWarn(String msg) {
		logger.warn(msg);
	}
}
