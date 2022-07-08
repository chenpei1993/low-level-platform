create table info
(
    id            int auto_increment,
    name          varchar(50)       not null,
    associationId int               not null,
    title         varchar(50)       null,
    pageTitle     varchar(50)       null,
    beginDateTime datetime          null,
    endDateTime   datetime          null,
    isDeleted     tinyint default 0 not null,
    deletedAt     datetime          null,
    deletedBy     int               null,
    createdAt     datetime          not null,
    createdBy     int               null,
    updatedAt     datetime          null,
    updatedBy     int               null,
    constraint info_pk
        primary key (id)
);
