use  manager;
create table if not exists setting(
    id                int auto_increment,
    name          varchar(1000)               not null,
    `key`          varchar(1000)         not null,
    value          varchar(1000)         null,
    is_deleted     tinyint           default 0 not null,
    deleted_at     timestamp          null,
    deleted_by     int               null,
    created_at     timestamp         default current_timestamp not null,
    created_by     int               not null,
    updated_at     timestamp         default current_timestamp not null,
    updated_by     int               not null,
    constraint setting_pk
    primary key (id)
);

insert into setting (name, `key`, value, created_by, updated_by)
    value ('前端地址', 'QUESTION_FRONT_URL', '127.0.0.1:8081', 1, 1);