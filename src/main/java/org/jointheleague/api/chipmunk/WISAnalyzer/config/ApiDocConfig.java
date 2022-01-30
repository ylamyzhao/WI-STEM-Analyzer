package org.jointheleague.api.chipmunk.WISAnalyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class ApiDocConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Level 7 Wi-Stem Analyzer",
                "League of Amazing Programmers Level 7 Wi-Stem Analyzer",
                "1.0.0",
                null,
                new Contact("Amy Zhao", "www.jointheleague.org", "ylamyzhao@gmail.com"),
                null, null, Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
}

