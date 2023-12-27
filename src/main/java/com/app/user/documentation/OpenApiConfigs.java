package com.app.user.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;


@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfigs {
//    @Bean
//    public OpenAPI customOpenAPI(
//            @Value("${openapi.service.title}") String serviceTitle,
//            @Value("${openapi.service.version}") String serviceVersion) {
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//        .components(
//            new Components()
//                .addSecuritySchemes(
//                    securitySchemeName,
//                    new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")))
//        .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
//                .info(new Info().title(serviceTitle).version(serviceVersion));
//    }
}
