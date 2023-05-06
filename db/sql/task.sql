create table if not exists task (
    id                int auto_increment,
    type              int               not null,
    info_id            int               not null,
    execution_date_time datetime          not null,
    send_type        int       not null,
    send_message         varchar(1000)       not null,
    status            int               not null,
    is_deleted     tinyint default 0 not null,
    deleted_at     datetime          null,
    deleted_by     int               null,
    created_at     datetime          not null,
    created_by     int               null,
    updated_at     datetime          null,
    updated_by     int               null,
    constraint task_pk
        primary key (id)
);
