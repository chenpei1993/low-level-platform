create table customerTag
(
    id         int not null,
    customer_id int not null,
    tag_id      int not null,
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

create index customerTag_isDeleted_index
    on customerTag (is_deleted);

create index customerTag_customerId_index
    on customerTag (customer_id);

create index customerTag_tagId_index
    on customerTag (tag_id);

