package org.openhab.core.jsr223.internal.engine;

import org.openhab.core.jsr223.internal.shared.Event;
import org.openhab.core.jsr223.internal.shared.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleExecutionRunnable implements Runnable {
	static private final Logger logger = LoggerFactory.getLogger(RuleExecutionRunnable.class);
	
	private Rule rule;
	private Event event;

	public RuleExecutionRunnable(Rule rule, Event event) {
		this.rule = rule;
		this.event = event;
	}
	
	@Override
	public void run() {
		try {
			this.rule.execute(event);
		}
		catch(Exception e) {
			logger.error("Error while executing rule: "+rule.getName(), e);
		}
	}

}
