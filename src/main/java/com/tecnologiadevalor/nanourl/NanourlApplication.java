package com.tecnologiadevalor.nanourl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NanourlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NanourlApplication.class, args);
	}

}
