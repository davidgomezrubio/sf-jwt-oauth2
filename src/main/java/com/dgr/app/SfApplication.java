package com.dgr.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SfApplication implements CommandLineRunner {

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		String password = "123456";
//
//		for (int i = 1; i < 4; i++) {
//			System.out.println(passwordEncoder.encode(password));
//		}


	}

}
