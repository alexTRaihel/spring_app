insert into user_db.user_credentials (id, username, password)
    values (1, 'admin', 'admin');

insert into user_db.user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN'), (1, 'SERVICE');