package com.AureloBank.Aurelo_Bank_Service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Aurelo Bank Application",
				description = "Backend REST Api's for Aurelo Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Vishal Gite",
						email = "vshlsgite.98.vg@gmail.com",
						url = "http/github.com"
				)
		)
)
public class AureloBankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AureloBankServiceApplication.class, args);
	}
}
