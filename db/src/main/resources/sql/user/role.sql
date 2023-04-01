create table if not exists role(
    id                int auto_increment,
    name          varchar(1000)               not null,
    department          varchar(1000)                not null,
    department_id          int                not null,
    status            int            default 1 not null,
    is_deleted     tinyint           default 0 not null,
    deleted_at     timestamp          null,
    deleted_by     int               null,
    created_at     timestamp         default current_timestamp not null,
    created_by     int               not null,
    updated_at     timestamp         default current_timestamp not null,
    updated_by     int               not null,
    constraint role_pk
    primary key (id)
)