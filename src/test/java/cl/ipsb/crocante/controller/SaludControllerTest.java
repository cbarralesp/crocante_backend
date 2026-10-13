package cl.ipsb.crocante.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Pruebas del endpoint {@code GET /api/salud} y de su aparición en la documentación de Swagger.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class SaludControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void respondeOkEnJson() throws Exception {
        mvc.perform(get("/api/salud"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    @Test
    void swaggerListaElEndpoint() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/salud']").exists());
    }
}
