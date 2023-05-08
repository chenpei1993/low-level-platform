create table if not exists question (
    id                int auto_increment,
    idx              int               not null,
    type              int               not null,
    info_id           int               not null,
    is_required       tinyint          not null,
    question_desc     varchar(3000)       not null,
    options         varchar(3000)    ,
    is_deleted     tinyint default 0 not null,
    deleted_at     datetime          null,
    deleted_by     int               null,
    created_at     datetime          not null,
    created_by     int               null,
    updated_at     datetime          null,
    updated_by     int               null,
    constraint question_pk
    primary key (id)
);