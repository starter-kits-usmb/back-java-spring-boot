create table exemples
(
    id bigserial primary key,
    description varchar(200),
    published boolean,
    title varchar(100)
);

alter table exemples owner to postgres;

CREATE TABLE roles
(
    id bigserial PRIMARY KEY,
    name varchar(20) CONSTRAINT roles_name_check CHECK (name IN ('USER', 'ADMIN'))
);

INSERT INTO roles (name) VALUES ('USER'), ('ADMIN');

alter table roles owner to postgres;

create table users
(
    id bigserial primary key,
    password varchar(120),
    username varchar(20)
        constraint users_username_key unique,
    role_id bigint not null constraint fk_users_roles references roles
);

alter table users owner to postgres;

