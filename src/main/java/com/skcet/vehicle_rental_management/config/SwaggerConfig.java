package com.skcet.vehicle_rental_management.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    final String securitySchemeName = "bearerAuth";
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("VehicleRentalSystem")
                .description("this is the vehicle rental system")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Niresh")
                    .email("727724eucs170@skcet.ac.in")
                    .url("https://github.com/NIRESHskcet"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://apache.org/licenses/LICENSE-2.0")))
            .servers(
                List.of(
                    new Server()
                    .url("http://localhost:8080")
                )
            )
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
