package com.cybrilla.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.regex("/api.*"))
				.apis(RequestHandlerSelectors.basePackage("com.cybrilla.controller")).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("aspire emi managment service")
				.description("this page list all Api's of aspire managment").version("1.0")
				.contact(new Contact("Aspire", "https://www.letsaspire.in/", "gopal@aspire.in")).license("license 1.0")
				.licenseUrl("https://www.letsaspire.in/").build();
	}
}
