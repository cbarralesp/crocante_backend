-- Categorías de la tienda (papas fritas, empanadas, bebidas...)
create table categorias (
    id      bigserial    primary key,
    nombre  varchar(80)  not null unique
);

-- Productos que se venden. Cada producto pertenece a una categoría.
create table productos (
    id            bigserial      primary key,
    nombre        varchar(120)   not null,
    precio        numeric(12, 2) not null check (precio > 0),
    disponible    boolean        not null default true,
    categoria_id  bigint         not null,
    constraint fk_productos_categorias
        foreign key (categoria_id) references categorias (id)
);

create index ix_productos_categoria on productos (categoria_id);
