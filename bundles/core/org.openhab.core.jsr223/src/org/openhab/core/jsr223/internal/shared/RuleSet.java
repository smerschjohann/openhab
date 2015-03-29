package org.openhab.core.jsr223.internal.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Interchangeable class which is created by a script instance. It will add its rules * 
 * 
 * TODO: possible addition: interface listener which informs <code>Script</code> that rules changed
 * currently the rules are not changeable after the RuleSet is returned to openhab
 * 
 * @author Simon Merschjohann
 *
 */
public class RuleSet {
	private List<Rule> rules;
	
	public RuleSet() {
		this.rules = new ArrayList<Rule>();
	}
	
	public RuleSet(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}
	
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}
	
	public void removeRule(Rule rule) {
		this.rules.remove(rule);
	}
	
	public List<Rule> getRules() {
		return this.rules;
	}
}
