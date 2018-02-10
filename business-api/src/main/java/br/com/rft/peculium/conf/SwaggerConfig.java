package br.com.rft.peculium.conf;
/*package br.com.ft.crestaurant.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String REST_API_NAME = "My REST API";
	private static final String REST_API_DESCRIPTION = "Some custom description of API.";
	private static final String REST_API_TOS = "API TOS";
	private static final String REST_API_TERM_OF_SERVICE = "Terms of service";
	private static final String REST_API_CONTACT = "myeaddress@company.com";
	private static final String REST_API_LICENSE = "License of API";
	private static final String REST_API_LICENSE_URL = "API license URL";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfo(REST_API_NAME, REST_API_DESCRIPTION, REST_API_TOS, REST_API_TERM_OF_SERVICE,
				REST_API_CONTACT, REST_API_LICENSE, REST_API_LICENSE_URL);
	}
}
*/