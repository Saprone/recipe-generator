package com.saprone.recipe_generator.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
            title = "OpenApi specification - Recipe Generator",
            description = "OpenApi documentation for Recipe Generator",
            version = "1.0",
            contact = @Contact(
                name = "Saprone",
                url = "https://recipe-generator-dkhegfefgtgecea9.northeurope-01.azurewebsites.net/"
            )
        ),
        servers = {
            @Server(
                description = "Production Environment",
                url = "https://recipe-generator-dkhegfefgtgecea9.northeurope-01.azurewebsites.net/"
            ),
            @Server(
                description = "Local Environment",
                url = "http://localhost:9191/"
            )
        }
)
public class OpenApiConfiguration {
}
