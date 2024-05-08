package com.india.railway;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.rule.Rule;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.example.rules.Fact;
import com.example.rules.RuleActivationListener;

import lombok.extern.slf4j.Slf4j; 


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.india.railway.repository")
@EnableMongoRepositories(basePackages = "com.india.railway.repository")
@Slf4j
public class  IndianRailway{
	public static void main(String[] args) {

		// countRulesInDroolsFile();
		SpringApplication.run(IndianRailway.class, args);
	}
	
	private static void countRulesInDroolsFile() {

		// Load DRL files into a KnowledgeBase
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("rule.drl"), ResourceType.DRL);
		// return 0;

		// Check for errors in DRL files
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
		}

		// Create a KnowledgeBase
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		// Create a StatefulKnowledgeSession
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		Fact fact1 = new Fact(100); // value > 10
		// Fact fact2 = new Fact(3);
		ksession.insert(fact1);

		// Create a listener to track fired rules
		RuleActivationListener activationListener = new RuleActivationListener();
		ksession.addEventListener(activationListener);

		// Insert your facts here

		// Fire all rules
		int totalrules = ksession.fireAllRules();

		Collection<Rule> allrules = ksession.getKnowledgeBase().getKnowledgePackage("com.example.rules").getRules();

		Iterator<Rule> iterator = allrules.iterator();

		while (iterator.hasNext()) {
			Rule rule = iterator.next();
		}

		System.out.println(totalrules);

		Set<String> activatedRuleNames = activationListener.getActivatedRuleNames();

		Set<String> allRuleNames = new HashSet<>();

		allRuleNames.removeAll(activatedRuleNames);

		System.out.println("Rules not fired:");
		for (String ruleName : allRuleNames) {
			System.out.println(ruleName);
		}

		ksession.dispose();

	}
	
	  @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("ghussenaiah@gmail.com");
	        mailSender.setPassword("WANAparthy@922");

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        return mailSender;
	    }
}
	    


