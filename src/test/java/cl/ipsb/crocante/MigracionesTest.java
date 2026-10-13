package cl.ipsb.crocante;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Comprueba que Flyway creó las tablas y cargó las categorías iniciales al arrancar.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootTest
class MigracionesTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void flywayCargoLasCategoriasIniciales() {
        Integer total = jdbc.queryForObject("select count(*) from categorias", Integer.class);
        assertEquals(5, total);
    }

    @Test
    void latablaProductosExisteYEstaVacia() {
        Integer total = jdbc.queryForObject("select count(*) from productos", Integer.class);
        assertEquals(0, total);
    }
}
