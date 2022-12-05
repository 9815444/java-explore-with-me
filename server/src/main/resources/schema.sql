-- drop table if exists users cascade;
-- drop table if exists events cascade;
-- drop table if exists categories cascade;
-- drop table if exists requests cascade;
-- drop table if exists compilation cascade;
-- drop table if exists compilation_event cascade;

CREATE TABLE IF NOT EXISTS categories
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name varchar(255)                            not null,
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT uq_categories_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS users
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255)                            NOT NULL,
    email VARCHAR(512)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS locations
(
    id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    lat float,
    lon float,
    constraint pk_location primary key (id)
);

CREATE TABLE IF NOT EXISTS events
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    category_id        bigint                                  not null references categories (id),
    user_id            bigint                                  not null references users (id),
    location_id        bigint                                  not null references locations (id),
    paid               bool,
    request_moderation bool,
    participant_limit  bigint,
    annotation         VARCHAR                                 NOT NULL,
    description        varchar                                 not null,
    title              varchar                                 not null,
    state              varchar(255)                            not null,
    confirmed_requests bigint,
    created_on         timestamp,
    published_on       timestamp,
    event_date         timestamp                               not null,
    views              bigint,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

create table if not exists compilation
(
    id     bigint generated by default as identity not null,
    pinned bool,
    title  varchar,
    constraint pk_compilation primary key (id)
);

create table if not exists compilation_event
(
    id             bigint generated by default as identity not null,
    event_id       bigint references events (id),
    compilation_id bigint references compilation (id),
    constraint pk_compilation_event primary key (id)
);

create table if not exists requests
(
    id        bigint generated by default as identity not null,
    created   timestamp,
    event     bigint,
    requester bigint,
    status    varchar,
    CONSTRAINT pk_requests PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id bigint not null references users (id),
    event_id bigint not null references events (id),
    published bool,
    text varchar,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);











