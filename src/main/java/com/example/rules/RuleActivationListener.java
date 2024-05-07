package com.example.rules;

import java.util.HashSet;
import java.util.Set;

import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.AgendaEventListener;
import org.drools.event.rule.AgendaGroupPoppedEvent;
import org.drools.event.rule.AgendaGroupPushedEvent;
import org.drools.event.rule.BeforeActivationFiredEvent;
import org.drools.event.rule.RuleFlowGroupActivatedEvent;
import org.drools.event.rule.RuleFlowGroupDeactivatedEvent;
import org.drools.runtime.rule.Activation;




 public class RuleActivationListener implements AgendaEventListener {
    private Set<String> activatedRuleNames = new HashSet<>();

    @Override
    public void afterActivationFired(AfterActivationFiredEvent event) {
        Activation activation = event.getActivation();
        String ruleName = activation.getRule().getName();
        activatedRuleNames.add(ruleName);
    }

    @Override
    public void activationCreated(org.drools.event.rule.ActivationCreatedEvent event) {
        // Not needed for this implementation
    }

    @Override
    public void activationCancelled(org.drools.event.rule.ActivationCancelledEvent event) {
        // Not needed for this implementation
    }

    // Other methods are not needed for this implementation

    public Set<String> getActivatedRuleNames() {
        return activatedRuleNames;
    }

	@Override
	public void beforeActivationFired(BeforeActivationFiredEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agendaGroupPushed(AgendaGroupPushedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
		// TODO Auto-generated method stub
		
	}
}
	