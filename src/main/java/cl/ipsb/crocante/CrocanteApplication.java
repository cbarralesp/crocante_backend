package cl.ipsb.crocante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Crocante.
 * <p>
 * {@code @SpringBootApplication} activa la configuración automática, busca los componentes del
 * paquete {@code cl.ipsb.crocante} y arranca el servidor Tomcat embebido.
 *
 * @author cbarrale
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
public class CrocanteApplication {

    /**
     * Arranca el contenedor de Spring y el servidor web.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        SpringApplication.run(CrocanteApplication.class, args);
    }
}
