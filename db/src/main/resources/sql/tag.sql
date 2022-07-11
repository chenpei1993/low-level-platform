create table tag
(
    id            int auto_increment,
    name          varchar(50)       not null,
    color         varchar(10)       null,

    is_deleted     tinyint default 0 not null,
    deleted_at     datetime          null,
    deleted_by     int               null,
    created_at     datetime          not null,
    created_by     int               null,
    updated_at     datetime          null,
    updated_by     int               null,
    constraint tag_pk
        primary key (id)
);