package cl.ipsb.crocante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipsb.crocante.model.Categoria;

/**
 * Repository: acceso a la tabla {@code categorias}. Spring Data genera la implementación.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
