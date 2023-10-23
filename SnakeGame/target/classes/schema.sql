CREATE SCHEMA IF NOT EXISTS user_info;
CREATE TABLE IF NOT EXISTS users
(
    id serial NOT NULL ,
    email character varying(30) NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_email_key UNIQUE (username)
        INCLUDE(email)
)