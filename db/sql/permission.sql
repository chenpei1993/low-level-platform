create table if not exists permission(
        id                int auto_increment,
        name          varchar(1000)               not null,
        permission          varchar(1000)                not null,
        status            int            default 1 not null,
        is_deleted     tinyint           default 0 not null,
        deleted_at     timestamp          null,
        deleted_by     int               null,
        created_at     timestamp         default current_timestamp not null,
        created_by     int               not null,
        updated_at     timestamp         default current_timestamp not null,
        updated_by     int               not null,
        constraint user_pk
        primary key (id)
    );

# 用户权限
insert into permission (name, permission, created_by, updated_by)
SELECT '用户', 'user', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'user');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户查询', 'user:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'user:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户添加', 'user:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'user:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户修改', 'user:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'user:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户删除', 'user:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'user:del');