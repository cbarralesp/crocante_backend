package cl.ipsb.crocante.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import cl.ipsb.crocante.model.Producto;

/**
 * Pruebas del repositorio de productos contra la base de datos de prueba.
 * <p>
 * {@code @Transactional} deshace los cambios al terminar cada prueba, así la base queda limpia.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootTest
@Transactional
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository repositorio;

    private Producto nuevo(String nombre, String precio, long categoriaId) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(new BigDecimal(precio));
        p.setCategoriaId(categoriaId);
        return p;
    }

    @Test
    void guardaYAsignaId() {
        Producto guardado = repositorio.save(nuevo("Papas medianas", "2500", 1));
        assertNotNull(guardado.getId());
        assertTrue(guardado.isDisponible());
    }

    @Test
    void buscaPorId() {
        Long id = repositorio.save(nuevo("Empanada de queso", "1800", 2)).getId();
        assertEquals("Empanada de queso", repositorio.findById(id).orElseThrow().getNombre());
    }

    @Test
    void filtraPorCategoria() {
        repositorio.save(nuevo("Papas chicas", "1500", 1));
        repositorio.save(nuevo("Bebida 500 cc", "1200", 4));
        assertEquals(1, repositorio.findByCategoriaId(1L).size());
        assertEquals(1, repositorio.findByCategoriaId(4L).size());
    }
}
