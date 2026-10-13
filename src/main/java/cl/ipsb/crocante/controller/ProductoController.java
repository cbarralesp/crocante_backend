package cl.ipsb.crocante.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ipsb.crocante.model.Producto;
import cl.ipsb.crocante.repository.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller de productos: guarda y lista los productos de Crocante.
 * <p>
 * Primera versión: el Controller habla directamente con el Repository. Sirve para ver el
 * recorrido completo (Swagger, Controller, Repository, PostgreSQL). Más adelante se agregan
 * el Service, los DTO y la validación.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Registro de los productos de la tienda")
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    // Spring entrega el Repository ya creado (inyección de dependencias por constructor).
    private final ProductoRepository repositorio;

    /**
     * Crea el controller con el repositorio que necesita. Lo invoca Spring.
     *
     * @param repositorio acceso a la tabla {@code productos}
     */
    public ProductoController(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Guarda un producto nuevo. Atiende {@code POST /api/productos}.
     *
     * @param producto datos del producto recibidos como JSON; el id se ignora
     * @return {@code 201 Created} con el producto guardado y la cabecera {@code Location}
     */
    @PostMapping
    @Operation(summary = "Guardar un producto nuevo",
            description = "Guarda el producto en la base de datos y devuelve el producto con el id asignado.")
    @ApiResponse(responseCode = "201", description = "Producto guardado")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        log.info("--- inicio guardar producto - nombre: {} categoria id: {} ---",
                producto.getNombre(), producto.getCategoriaId());
        producto.setId(null); // el id lo decide la base de datos, no el cliente
        Producto guardado = repositorio.save(producto);
        log.info("--- fin guardar producto - id asignado: {} ---", guardado.getId());
        return ResponseEntity.created(URI.create("/api/productos/" + guardado.getId())).body(guardado);
    }

    /**
     * Lista los productos. Atiende {@code GET /api/productos}.
     *
     * @param categoriaId opcional: si viene, lista solo los productos de esa categoría
     * @return los productos encontrados; lista vacía si no hay ninguno
     */
    @GetMapping
    @Operation(summary = "Listar productos",
            description = "Sin parámetros lista todos. Con categoriaId lista solo los de esa categoría.")
    public List<Producto> listar(@RequestParam(required = false) Long categoriaId) {
        log.info("--- inicio listar productos - categoria id: {} ---", categoriaId);
        List<Producto> productos = categoriaId == null ? repositorio.findAll() : repositorio.findByCategoriaId(categoriaId);
        log.info("--- fin listar productos - total: {} ---", productos.size());
        return productos;
    }
}
