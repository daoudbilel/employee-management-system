package com.socle.springboot.security;//package com.socle.springboot.security;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
        apiSelectorBuilder.apis(withClassAnnotation(Api.class));
        return apiSelectorBuilder.build().pathMapping("/").useDefaultResponseMessages(false)
                .apiInfo(apiInfo()).forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Gestion d'employee").description("").license(" ").version("0.0.1").build();
    }
}
