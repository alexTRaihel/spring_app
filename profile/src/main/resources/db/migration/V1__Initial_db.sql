create schema if not exists profiles_db;

create table profiles_db.profile (
    id bigserial primary key,
    username varchar(256)
);

create table profiles_db.vehicle (
    id bigserial primary key,
    model varchar(256),
    profile_id bigserial
);

alter table if exists profiles_db.vehicle
  add constraint vehicle_usr_fk
  foreign key (profile_id) references profiles_db.profile;