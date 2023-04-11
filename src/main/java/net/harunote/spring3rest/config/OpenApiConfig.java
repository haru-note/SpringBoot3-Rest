package net.harunote.spring3rest.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .title("Haru note Application API")
            .description("The best way to manage my goals. harunote.net")
            .termsOfService("http://swagger.io/terms/")
            .contact(new Contact().name("villainscode").url("").email("harunote.net@gmail.com"))
            .license(new License().name("MIT"))
            .version("1.0")
        );
  }
}