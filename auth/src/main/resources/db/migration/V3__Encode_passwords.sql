create extension if not exists pgcrypto;

update user_credentials set password = crypt(password, gen_salt('bf', 8));