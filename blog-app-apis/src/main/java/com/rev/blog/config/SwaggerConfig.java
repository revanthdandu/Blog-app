package com.rev.blog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	
	   @Bean
	   public OpenAPI defineOpenApi() {
	       Server server = new Server();
	       server.setUrl("http://localhost:8080/v3/api-docs");
//	       server.setDescription("Development");

	       Contact myContact = new Contact();
	       myContact.setName("Revanth");
	       myContact.setEmail("revanthdandu99@gmail.com");

	       Info information = new Info()
	               .title("Blog App")
	               .version("1.0")
	               .description("This API exposes endpoints to manage blog")
	               .contact(myContact);
	       return new OpenAPI().info(information).servers(List.of(server));
	   }
	
	
	
	
	
//	@Bean
//	public Docket api() {
//
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}

//	private ApiInfo getInfo() {
//
//		return new ApiInfo("Blog app", "this is a timepass project", "1.0", null,
//				new Contact("Revanth", null, "revanthdandu99@gmail.com"), null, null, null);
//	}

}
