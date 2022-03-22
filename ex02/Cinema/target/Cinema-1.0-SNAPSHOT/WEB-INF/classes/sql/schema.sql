create table if not exists usr (
    firstName varchar,
    lastName varchar,
    email varchar NOT NULL UNIQUE,
    password varchar NOT NULL
);

create table if not exists authentications (
    dateTime timestamp,
    ip   varchar
);