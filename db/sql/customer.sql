create table customer
(
    id        int auto_increment,
    name      varchar(50)       not null,
    email     varchar(100)      null,
    phone     varchar(20)       null,

    is_deleted     tinyint default 0 not null,
    deleted_at     datetime          null,
    deleted_by     int               null,
    created_at     datetime          not null,
    created_by     int               null,
    updated_at     datetime          null,
    updated_by     int               null,
    constraint customer_pk
        primary key (id)
)
    comment '客户表';

create unique index customer_email_uindex
    on customer (email);

create unique index customer_phone_uindex
    on customer (phone);