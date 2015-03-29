package org.openhab.core.jsr223.internal.shared;

import org.openhab.core.items.Item;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;

public class TimerTrigger implements EventTrigger {

	private String cron;

	public TimerTrigger(String cron) {
		this.cron = cron;
	}
	
	@Override
	public String getItem() {
		return null;
	}

	@Override
	public boolean evaluate(Item item, State oldState, State newState,
			Command command, TriggerType type) {
		return type == TriggerType.TIMER;
	}

	public String getCron() {
		return this.cron;
	}
}
