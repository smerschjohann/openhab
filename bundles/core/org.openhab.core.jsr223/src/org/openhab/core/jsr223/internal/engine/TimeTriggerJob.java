package org.openhab.core.jsr223.internal.engine;

import org.openhab.core.jsr223.internal.engine.scriptmanager.ScriptManager;
import org.openhab.core.jsr223.internal.shared.Event;
import org.openhab.core.jsr223.internal.shared.Rule;
import org.openhab.core.jsr223.internal.shared.TriggerType;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeTriggerJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(TimeTriggerJob.class);
		
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String ruleName = (String) context.getJobDetail().getJobDataMap().get(RuleTriggerManager.RULE_NAME);				
		String scriptName = (String) context.getJobDetail().getJobDataMap().get(RuleTriggerManager.SCRIPT_FILE);
		
		logger.info("TimeTrigger for rule: "+ ruleName + ", scriptName: "+scriptName);
		
		ScriptManager manager = ScriptManager.getInstance();
		Rule rule = manager.getRule(scriptName, ruleName);
		
		manager.executeRules(new Rule[] { rule }, new Event(TriggerType.TIMER, null, null, null, null));
	}

}
