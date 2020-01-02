/**
 * 
 */
package com.owt.contact.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.owt.contact.constant.SecurityConstant;
import com.owt.contact.constant.SwaggerConstant;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author moussa.kanoute
 *
 */
@Configuration
@EnableSwagger2WebMvc
@Import({ SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class })
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(SwaggerConstant.API_NAME, SwaggerConstant.API_DESC, SwaggerConstant.API_VERSION,
				SwaggerConstant.API_TERMS_OF_SERVICE_URL,
				new Contact(SwaggerConstant.API_CONTACT_NAME, SwaggerConstant.API_TERMS_OF_SERVICE_URL,
						SwaggerConstant.API_CONTACT_EMAIL),
				"License of API", "API license URL", Collections.emptyList());
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientSecret(SecurityConstant.SECRET).scopeSeparator(" ")
				.useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

	private SecurityScheme securityScheme() {

		return new ApiKey(SwaggerConstant.APIKEY_NAME, SecurityConstant.HEADER_JWT, "");

	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("User", "Allow contact to manage his data"),
				new AuthorizationScope("Admin", "Allow administrator to manage contacts") };
		return scopes;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference(SwaggerConstant.APIKEY_NAME, scopes())))
				.forPaths(PathSelectors.any()).build();
	}
}
