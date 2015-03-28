/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.core.jsr223.internal;

//import org.openhab.model.rule.RulesStandaloneSetup;
import org.openhab.core.scriptengine.action.ActionService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Extension of the default OSGi bundle activator
 * 
 * @author Simon Merschjohann
 * @since 0.1
 */
public class Jsr223CoreActivator implements BundleActivator {

	private final static Logger logger = LoggerFactory.getLogger(Jsr223CoreActivator.class);
	public static ServiceTracker<ActionService, ActionService> actionServiceTracker;

	public void start(BundleContext bc) throws Exception {
		actionServiceTracker = new ServiceTracker<ActionService, ActionService>(bc, ActionService.class, null);
		actionServiceTracker.open();
		
		//RulesStandaloneSetup.doSetup();
		logger.debug("Registered 'jsr223' configuration parser");	
	}

	public void stop(BundleContext context) throws Exception {
	}

}
