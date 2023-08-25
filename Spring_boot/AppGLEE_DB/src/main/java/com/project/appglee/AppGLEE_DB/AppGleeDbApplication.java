package com.project.appglee.AppGLEE_DB;

import com.project.appglee.AppGLEE_DB.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class AppGleeDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGleeDbApplication.class, args);
	}

}
