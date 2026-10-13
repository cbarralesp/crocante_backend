package cl.ipsb.crocante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipsb.crocante.model.Producto;

/**
 * Repository: acceso a la tabla {@code productos}.
 * <p>
 * Solo declaramos la interfaz. Spring Data genera la implementación al arrancar:
 * {@code save}, {@code findById}, {@code findAll}, {@code deleteById} y más ya vienen
 * incluidos en {@link JpaRepository}.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca los productos de una categoría. Spring Data lee el nombre del método y arma la
     * consulta {@code where categoria_id = ?}.
     *
     * @param categoriaId id de la categoría
     * @return los productos de esa categoría; vacío si no tiene ninguno
     */
    List<Producto> findByCategoriaId(Long categoriaId);
}
