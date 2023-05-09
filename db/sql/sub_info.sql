create table if not exists sub_info
(
    id            int auto_increment,
    parent_id     int               not null,
    start_date_time datetime          null,
    end_date_time   datetime          null,
    is_deleted     tinyint default 0 not null,
    deleted_at     datetime          null,
    deleted_by     int               null,
    created_at     datetime          not null,
    created_by     int               null,
    updated_at     datetime          null,
    updated_by     int               null,
    constraint info_pk
        primary key (id)
);

