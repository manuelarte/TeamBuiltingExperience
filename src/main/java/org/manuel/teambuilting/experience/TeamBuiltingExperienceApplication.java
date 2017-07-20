package org.manuel.teambuilting.experience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TeamBuiltingExperienceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingExperienceApplication.class, args);
	}

}