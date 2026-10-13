package cl.ipsb.crocante.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Model (entidad JPA): cada objeto {@code Producto} es una fila de la tabla {@code productos}.
 * <p>
 * Las anotaciones le dicen a Hibernate cómo se corresponde la clase con la tabla.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "productos")
public class Producto {

    /** Identificador del producto. Lo asigna PostgreSQL (columna {@code bigserial}). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el id lo asigna PostgreSQL (bigserial)
    private Long id;

    /** Nombre del producto tal como se muestra en la lista (máximo 120 caracteres). */
    @Column(nullable = false, length = 120)
    private String nombre;

    /** Precio de venta. Se usa {@link BigDecimal} porque {@code double} no es exacto para dinero. */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    /** Indica si el producto está disponible para la venta. Por defecto es verdadero. */
    @Column(nullable = false)
    private boolean disponible = true;

    /**
     * Id de la categoría a la que pertenece el producto. Por ahora se guarda solo el id;
     * más adelante será una relación entre entidades.
     */
    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

    /** Constructor vacío que exige JPA. */
    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
