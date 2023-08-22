package com.ifsju.interfaceweb.sign.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Title",
        description = "Description",
        version = "1.0.0")
)
@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi ghatOpenApi(){
        String[] paths ={"/sign/**"};

        return GroupedOpenApi.builder()
                .group("temp")
                .pathsToMatch(paths)
                .build();
    }
}
