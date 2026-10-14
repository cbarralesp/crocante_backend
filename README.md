# crocante_backend

API del sistema de venta de comida rápida Crocante (Spring Boot 3.5, Java 21, PostgreSQL). Se construye por pasos durante las clases; cada paso es un commit con su tag.

| Tag | Qué se agrega |
|---|---|
| `paso-00` | Proyecto que arranca, con Swagger UI |
| `paso-01` | Primer Controller (`GET /api/salud`) |
| `paso-02` | Conexión a PostgreSQL y migraciones Flyway (tablas `categorias` y `productos`) |
| `paso-03` | Entidad `Producto` y `ProductoRepository` |
| `paso-04` | `ProductoController`: guardar y listar productos |
| `paso-05` | `CategoriaController`: listar categorías (lo usa el frontend Angular) |

```bash
git checkout paso-02     # ver el proyecto en ese punto
git checkout main        # volver al último paso
```

## 1. Crear la base de datos (una sola vez, desde el paso 02)

Conectado como `postgres` (pgAdmin o psql), ejecutar `db/crear-base-de-datos.sql`:

```sql
CREATE ROLE crocante WITH LOGIN PASSWORD 'crocante';
CREATE DATABASE crocante OWNER crocante ENCODING 'UTF8';
```

Las tablas no se crean a mano: Flyway las crea al arrancar. Si usas otro usuario, clave o puerto, define `DB_URL`, `DB_USER` y `DB_PASSWORD`.

## 2. Ejecutar

```bash
./mvnw spring-boot:run
```

En Eclipse: *File → Import → Maven → Existing Maven Projects* y luego *Run As → Spring Boot App* sobre `CrocanteApplication`.

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Estado: http://localhost:8080/api/salud

## 3. Probar en Swagger

1. Abrir `POST /api/productos` y pulsar **Try it out**.
2. Reemplazar el cuerpo por: `{"nombre": "Empanada de queso", "precio": 1800, "categoriaId": 2}`.
3. **Execute**. Debe responder `201` con el producto y su `id`.
4. Probar `GET /api/productos` para ver lo guardado, o con `categoriaId=2` para filtrar.

Categorías cargadas por Flyway: 1 Papas fritas, 2 Empanadas, 3 Sopaipillas, 4 Bebidas, 5 Salsas (también con `GET /api/categorias`).

## 4. Frontend

El frontend Angular está en su propio repositorio, https://github.com/cbarralesp/crocante_frontend, y se conecta a este backend por el puerto 8080 (ver su README).

## Logs y Javadoc

**Logs.** Cada clase declara su *logger* con SLF4J (`private static final Logger log = LoggerFactory.getLogger(...)`) y registra el inicio y el fin de cada proceso con el formato `--- inicio guardar producto - nombre: {} ---` / `--- fin ... ---`. El nivel se configura en la sección `logging` de `application.yml`: INFO en general y DEBUG para `cl.ipsb.crocante` y para el SQL de Hibernate (`org.hibernate.SQL`). No se registran datos sensibles.

**Javadoc.** Cada clase lleva una cabecera con descripción y las etiquetas `@author cbarrale`, `@version 1.0.0` y `@since 1.0.0`; los métodos públicos documentan `@param` y `@return`. Para generar el sitio de documentación:

```bash
./mvnw javadoc:javadoc    # resultado en target/reports/apidocs/index.html
```

## Pruebas automáticas

```bash
./mvnw test
```

Usan H2 en modo PostgreSQL, así que no necesitan PostgreSQL instalado (13 pruebas).

## Avisos

- Flyway muestra un `WARN` indicando que PostgreSQL 18 es más nuevo que su última versión probada (17). La aplicación funciona; es solo un aviso.
- En esta primera versión una categoría inexistente responde `500`. Se corrige más adelante con el Service y el manejo global de errores.
