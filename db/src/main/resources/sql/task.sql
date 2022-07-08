create table task
(
    id                int auto_increment,
    type              int               not null,
    infoId            int               not null,
    executionDateTime datetime          not null,
    status            int               not null,
    isDeleted         tinyint default 0 not null,
    deletedAt         datetime          null,
    deletedBy         int               null,
    createdAt         datetime          null,
    createdBy         int               null,
    updatedAt         datetime          null,
    updatedBy         int               null,
    constraint task_pk
        primary key (id)
);
