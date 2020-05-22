package com.devgd.melonclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MelonCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MelonCloneApplication.class, args);

		
		// ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);
		// ctx.getBean("testRepository");
		// MenuHandler.menuInit();
		// SpringApplication.run(TestRunnber.class, args);
	}

}
