package com.mynt.exam.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

	private static final KieServices kieServices = KieServices.Factory.get();
	
	@Bean(name="kieContainer")
	public KieContainer kieContainer() {
	   return kieServices.getKieClasspathContainer();
	}
}
