create table customerTag
(
    id         int not null,
    customerId int not null,
    tagId      int not null
);

create index customerTag_customerId_index
    on customerTag (customerId);

create index customerTag_tag_id_index
    on customerTag (tagId);

