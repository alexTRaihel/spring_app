create schema if not exists services_db;

create table services_db.service_station (
    id bigserial primary key,
    name varchar(256) not null,
    address varchar(256) not null,
    access boolean,
    latitude double precision,
    longitude double precision,
    city integer
);