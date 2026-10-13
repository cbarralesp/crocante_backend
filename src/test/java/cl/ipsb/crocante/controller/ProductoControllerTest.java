package cl.ipsb.crocante.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Pruebas de los endpoints de productos ({@code POST} y {@code GET /api/productos}).
 * <p>
 * {@code @Transactional} deshace lo guardado al terminar cada prueba, así la base queda limpia.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductoControllerTest {

    @Autowired
    private MockMvc mvc;

    private String json(String nombre, String precio, long categoriaId) {
        return "{\"nombre\":\"" + nombre + "\",\"precio\":" + precio + ",\"categoriaId\":" + categoriaId + "}";
    }

    @Test
    void guardaUnProductoYDevuelve201ConElId() throws Exception {
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON)
                        .content(json("Papas medianas", "2500", 1)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nombre").value("Papas medianas"))
                .andExpect(jsonPath("$.disponible").value(true));
    }

    @Test
    void ignoraElIdEnviadoPorElCliente() throws Exception {
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":999,\"nombre\":\"Sopaipilla\",\"precio\":800,\"categoriaId\":3}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(org.hamcrest.Matchers.not(999)));
    }

    @Test
    void listaLosProductosGuardadosYFiltraPorCategoria() throws Exception {
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON)
                .content(json("Papas chicas", "1500", 1)));
        mvc.perform(post("/api/productos").contentType(MediaType.APPLICATION_JSON)
                .content(json("Bebida 500 cc", "1200", 4)));

        mvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
        mvc.perform(get("/api/productos").param("categoriaId", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Bebida 500 cc"));
    }

    /**
     * Problema conocido de esta primera versión: una categoría inexistente rompe la clave foránea
     * y nadie traduce el error a una respuesta HTTP clara (en la aplicación real se ve como 500).
     * Lo resolvemos más adelante con el Service y el manejo global de errores.
     */
    @Test
    void categoriaInexistenteProvocaUnErrorSinTratar() {
        assertThrows(Exception.class, () -> mvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json("Fantasma", "1000", 999))));
    }
}
