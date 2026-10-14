package cl.ipsb.crocante.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ipsb.crocante.model.Categoria;
import cl.ipsb.crocante.repository.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller de categorías. Es de solo lectura por ahora: el formulario del frontend necesita
 * la lista para elegir una categoría.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "Categorías de los productos")
public class CategoriaController {

    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    private final CategoriaRepository repositorio;

    /**
     * Crea el controller con el repositorio que necesita. Lo invoca Spring.
     *
     * @param repositorio acceso a la tabla {@code categorias}
     */
    public CategoriaController(CategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Lista todas las categorías ordenadas por id. Atiende {@code GET /api/categorias}.
     *
     * @return las categorías registradas
     */
    @GetMapping
    @Operation(summary = "Listar categorías", description = "Devuelve todas las categorías ordenadas por id.")
    public List<Categoria> listar() {
        log.info("--- inicio listar categorias ---");
        List<Categoria> categorias = repositorio.findAll(Sort.by("id"));
        log.info("--- fin listar categorias - total: {} ---", categorias.size());
        return categorias;
    }
}
