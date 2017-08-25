package org.manuel.teambuilting.experience;

import org.manuel.teambuilting.core.config.EnableCoreFunctionalities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableCoreFunctionalities
@SpringBootApplication
public class ExperienceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ExperienceApplication.class, args);
	}

}