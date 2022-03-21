create table if not exists usr (
    firstName varchar,
    lastName varchar,
    email varchar NOT NULL UNIQUE,
    password varchar NOT NULL
);