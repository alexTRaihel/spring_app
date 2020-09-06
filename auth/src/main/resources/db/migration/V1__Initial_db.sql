create schema if not exists user_db;

create table user_db.user_credentials (
    id bigserial primary key,
    username varchar(64),
    password varchar(64)
);

create table user_db.user_role (
  user_id bigint not null,
  roles varchar(255)
);

alter table if exists user_db.user_role
  add constraint user_role_user_credentials_fk
  foreign key (user_id) references user_db.user_credentials(id);