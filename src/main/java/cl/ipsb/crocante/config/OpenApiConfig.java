package cl.ipsb.crocante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuración de Swagger (OpenAPI): el título, la versión y la descripción que se ven en la
 * parte superior de la página {@code /swagger-ui/index.html}.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class OpenApiConfig {

    /**
     * Crea la descripción general de la API que muestra Swagger UI.
     *
     * @return información de la API de Crocante
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info()
                .title("API Crocante")
                .version("0.0.1")
                .description("Sistema de venta de comida rápida: registro de productos por categoría."));
    }
}
