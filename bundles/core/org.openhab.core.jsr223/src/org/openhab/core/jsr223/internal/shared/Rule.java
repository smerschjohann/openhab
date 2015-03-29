package org.openhab.core.jsr223.internal.shared;

import java.util.List;

public interface Rule {
	public String getName();

    public List<EventTrigger> getEventTrigger();

    public void execute(Event event);
    
    
}
