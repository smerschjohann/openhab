class TestRule(Rule):
	def getName(self):
		return "TestRule"
	
	def getEventTrigger(self):
		return [
			StartupTrigger(),
			ChangedEventTrigger("Heating_FF_Child", None, None),
			TimerTrigger("0/50 * * * * ?")
		]
		
	def execute(self, event):
		oh.logDebug(self.getName(), "event received " + str(event))
		oh.logInfo(self.getName(), str(ItemRegistry.getItem("Heating_GF_Corridor")))
		action = oh.getActions() 
		oh.logInfo(self.getName(), "available actions: " + str(action.keySet()))
		ping = oh.getAction("Ping")
		oh.logInfo(self.getName(), "internet reachable: " + ("yes" if ping.checkVitality("google.com", 80, 100) else "no"))
			
def getRules():
	return RuleSet([
		TestRule()
	])
