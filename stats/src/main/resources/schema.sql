drop table if exists stats;
CREATE TABLE IF NOT EXISTS stats
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app       VARCHAR(255)                            NOT NULL,
    uri       varchar(255)                            not null,
    ip        varchar(255)                            not null,
    date_time timestamp                               not null,
    CONSTRAINT pk_stats PRIMARY KEY (id)
);










