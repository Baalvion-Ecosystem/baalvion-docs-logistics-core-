package com.baalvion.documentvault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI documentVaultOpenAPI() {
		return new OpenAPI().info(new Info().title("Document Vault Service API")
				.description("Enterprise Document Vault Service - Baalvion Platform").version("v1.0.0")
				.contact(new Contact().name("Baalvion Team")));
	}
}