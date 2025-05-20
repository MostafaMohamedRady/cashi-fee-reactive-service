package com.cashi.feeservice.transaction.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Transaction Fee Service API")
                    .version("1.0")
                    .description("API for calculating and storing transaction fees")
                    .contact(
                        Contact()
                            .name("Mostafa Rady")
                            .email("rady.m.mostafa@gmail.com")
                    )
            )
    }
}
