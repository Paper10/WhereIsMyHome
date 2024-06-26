package com.ssafy.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "김김SSAFY VUE API 명세서",
	        description = "<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 VUE API<br><img src=\"/img/ssafy_logo.png\" width=\"150\">",
	        version = "v1",
	        contact = @Contact(
	            name = "hissam",
	            email = "hissam@ssafy.com",
	            url = "http://edu.ssafy.com"
	        )
	    )
	)
@Configuration
public class SwaggerConfiguration {

	@Bean
	public GroupedOpenApi allApi() {
		return GroupedOpenApi.builder().group("ssafy-all").pathsToMatch("/**").build();
	}

	@Bean
	public GroupedOpenApi boardApi() {
		return GroupedOpenApi.builder().group("ssafy-board").pathsToMatch("/board/**").build();
	}
	
	@Bean
	public GroupedOpenApi userApi() {
		return GroupedOpenApi.builder().group("ssafy-user").pathsToMatch("/user/**").build();
	}
	
	@Bean
	public GroupedOpenApi mapApi() {
		return GroupedOpenApi.builder().group("ssafy-map").pathsToMatch("/map/**").build();
	}

	@Bean
	public GroupedOpenApi aptApi() {
		return GroupedOpenApi.builder().group("ssafy-apt").pathsToMatch("/apt/**").build();
	}

	@Bean
	public GroupedOpenApi tradeApi() {
		return GroupedOpenApi.builder().group("ssafy-trade").pathsToMatch("/trade/**").build();
	}
}
