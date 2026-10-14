package cl.ipsb.crocante.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Model (entidad JPA): cada objeto {@code Categoria} es una fila de la tabla {@code categorias}.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    /** Identificador de la categoría. Lo asigna PostgreSQL. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la categoría, por ejemplo «Papas fritas» (máximo 80 caracteres). */
    @Column(nullable = false, length = 80)
    private String nombre;

    /** Constructor vacío que exige JPA. */
    public Categoria() {
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
}
