create table if not exists users(
id bigserial not null,
username varchar (255) not null unique,
password varchar (255) not null,
lastname varchar (255) ,
primary key (id),

is_account_non_expired      boolean not null ,
is_account_non_locked       boolean not null,
is_credentials_non_expired  boolean not null,
is_enabled                  boolean not null

);

create table if not exists roles
(
    id   bigint,
    role_name varchar,
    primary key (id)
);

create table if not exists roles_users
(
    user_id bigint,
    foreign key (user_id) references users (id),
    role_id bigint,
    foreign key (role_id) references roles (id)
);