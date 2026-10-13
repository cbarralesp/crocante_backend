package cl.ipsb.crocante.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller: la "puerta de entrada" HTTP de la aplicación.
 * <p>
 * Recibe una petición, decide qué método la atiende y devuelve una respuesta. Este controller
 * solo sirve para comprobar que la aplicación está viva.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/salud")
@Tag(name = "Salud", description = "Comprueba que la aplicación está viva")
public class SaludController {

    private static final Logger log = LoggerFactory.getLogger(SaludController.class);

    /**
     * Responde el estado de la aplicación. Atiende {@code GET /api/salud}.
     *
     * @return un mapa con el estado y el nombre de la aplicación; Jackson lo convierte a JSON
     */
    @GetMapping
    @Operation(summary = "Estado de la aplicación")
    public Map<String, String> estado() {
        log.debug("--- consulta del estado de la aplicación ---");
        return Map.of("estado", "ok", "aplicacion", "Crocante");
    }
}
