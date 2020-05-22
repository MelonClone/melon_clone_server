package com.devgd.melonclone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainRunner implements ApplicationRunner {
	
	@Autowired
	ApplicationContext context;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		

	}
	
}