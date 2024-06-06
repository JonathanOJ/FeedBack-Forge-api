package com.feedbackforge.feedbackforgeapi.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("FeedBackForgeApi")
                .version("1.0")
                .description("Aqui você encontra a documentação da API do FeedBackForge para auxiliar no desenvolvimento de aplicações que utilizam a API."));
    }
}
