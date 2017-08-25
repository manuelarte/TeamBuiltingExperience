package org.manuel.teambuilting.experience;

import org.manuel.teambuilting.core.config.EnableCoreFunctionalities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCoreFunctionalities
public class TeamBuiltingExperienceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingExperienceApplication.class, args);
	}

}