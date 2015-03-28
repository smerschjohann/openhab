package org.openhab.core.jsr223.internal.shared;

import java.util.HashMap;

import org.openhab.core.jsr223.internal.Jsr223CoreActivator;
import org.openhab.core.scriptengine.action.ActionService;


import org.openhab.model.script.actions.BusEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Openhab extends BusEvent {
	private static String LOGGER_NAME_PREFIX = "org.openhab.model.jsr232.";

	/**
	 * Returns all available action providers
	 * 
	 * @return map of all action providers
	 */
	public static HashMap<String, Object> getActions() {
		HashMap<String, Object> actions = new HashMap<String, Object>();
		
		Object[] services = Jsr223CoreActivator.actionServiceTracker.getServices();
		if(services!=null) {
			for(Object service : services) {
				ActionService actionService = (ActionService) service;
				
				String className = actionService.getActionClassName().substring(actionService.getActionClassName().lastIndexOf(".")+1);
				
				actions.put(className, actionService.getActionClass());
			}
		}
		
		return actions;
	}
	
	/**
	 * Get an action provider based on its name
	 * 
	 * @param action
	 * @return
	 */
	public static Object getAction(String action) {
		return getActions().get(action);
	}
	
	/**
	 * Creates the Log-Entry <code>format</code> with level <code>DEBUG</code>
	 * and logs under the loggers name <code>org.openhab.model.script.&lt;loggerName&gt;</code>
	 * 
	 * @param loggerName the name of the Logger which is prefixed with 
	 * <code>org.openhab.model.script.</code> 
	 * @param format the Log-Statement which can contain placeholders '<code>{}</code>'
	 * @param args the arguments to replace the placeholders contained in <code>format</code>
	 * 
	 * @see Logger
	 */
	static public void logDebug(String loggerName, String format, Object... args) {
		LoggerFactory.getLogger(LOGGER_NAME_PREFIX .concat(loggerName)).debug(format, args);
	}

	/**
	 * Creates the Log-Entry <code>format</code> with level <code>INFO</code>
	 * and logs under the loggers name <code>org.openhab.model.script.&lt;loggerName&gt;</code>
	 * 
	 * @param loggerName the name of the Logger which is prefixed with 
	 * <code>org.openhab.model.script.</code> 
	 * @param format the Log-Statement which can contain placeholders '<code>{}</code>'
	 * @param args the arguments to replace the placeholders contained in <code>format</code>
	 * 
	 * @see Logger
	 */
	static public void logInfo(String loggerName, String format, Object... args) {
		LoggerFactory.getLogger(LOGGER_NAME_PREFIX.concat(loggerName)).info(format, args);
	}
	
	/**
	 * Creates the Log-Entry <code>format</code> with level <code>WARN</code>
	 * and logs under the loggers name <code>org.openhab.model.script.&lt;loggerName&gt;</code>
	 * 
	 * @param loggerName the name of the Logger which is prefixed with 
	 * <code>org.openhab.model.script.</code> 
	 * @param format the Log-Statement which can contain placeholders '<code>{}</code>'
	 * @param args the arguments to replace the placeholders contained in <code>format</code>
	 * 
	 * @see Logger
	 */
	static public void logWarn(String loggerName, String format, Object... args) {
		LoggerFactory.getLogger(LOGGER_NAME_PREFIX.concat(loggerName)).warn(format, args);
	}
	
	/**
	 * Creates the Log-Entry <code>format</code> with level <code>ERROR</code>
	 * and logs under the loggers name <code>org.openhab.model.script.&lt;loggerName&gt;</code>
	 * 
	 * @param loggerName the name of the Logger which is prefixed with 
	 * <code>org.openhab.model.script.</code> 
	 * @param format the Log-Statement which can contain placeholders '<code>{}</code>'
	 * @param args the arguments to replace the placeholders contained in <code>format</code>
	 * 
	 * @see Logger
	 */
	static public void logError(String loggerName, String format, Object... args) {
		LoggerFactory.getLogger(LOGGER_NAME_PREFIX.concat(loggerName)).error(format, args);
	}
}
