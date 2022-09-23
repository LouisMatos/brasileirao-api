package br.com.luisfpmatos.brasileiraoapi.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Component
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
	
	
	private static final String BASE_PACKAGE = "br.com.luisfpmatos.brasileiraoapi.controller";
	private static final String API_TITULO = "Brasileirão API - Scraping";
	private static final String API_DESCRICAO = "API REST que contem dados de partidas do Brasileirão em tempo real";
	private static final String API_VERSAO = "1.0.0";
	private static final String CONTATO_NAME = "Luis Matos";
	private static final String CONTATO_GITHUB = "https://github.com/LouisMatos";
	private static final String CONTATO_EMAIL = "luisfpmatos@gmail.com";
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(buildApiInfo());
	}
	
	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
				.title(API_TITULO)
				.description(API_DESCRICAO)
				.version(API_VERSAO)
				.contact(new Contact(CONTATO_NAME, CONTATO_GITHUB, CONTATO_EMAIL))
				.build();
	}
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
}
