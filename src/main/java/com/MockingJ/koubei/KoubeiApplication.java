package com.MockingJ.koubei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.MockingJ.koubei.mapper")
public class KoubeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoubeiApplication.class, args);
	}
}
