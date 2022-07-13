create table customer
(
    id        int auto_increment,
    name      varchar(50)       not null,
    email     varchar(100)      null,
    phone     varchar(20)       null,
    isDeleted tinyint default 0 not null,
    deletedAt datetime          null,
    deletedBy int               null,
    createdBy int               not null,
    createdAt datetime          not null,
    updatedAt datetime          not null,
    updateBy  int               not null,
    constraint customer_pk
        primary key (id)
)
    comment '客户表';

create unique index customer_email_uindex
    on customer (email);

create unique index customer_phone_uindex
    on customer (phone);