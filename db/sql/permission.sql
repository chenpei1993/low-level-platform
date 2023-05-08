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

# 权限
insert into permission (name, permission, created_by, updated_by)
SELECT '权限', 'permission', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'permission');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限查询', 'permission:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'permission:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限添加', 'permission:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'permission:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限修改', 'permission:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'permission:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限删除', 'permission:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'permission:del');

# 用户
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

# 角色
insert into permission (name, permission, created_by, updated_by)
SELECT '角色', 'role', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'role');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色查询', 'role:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'role:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色添加', 'role:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'role:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色修改', 'role:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'role:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色删除', 'role:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'role:del');


# 标签
insert into permission (name, permission, created_by, updated_by)
SELECT '标签', 'tag', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'tag');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签查询', 'tag:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'tag:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签添加', 'tag:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'tag:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签修改', 'tag:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'tag:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签删除', 'tag:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'tag:del');


# 客户
insert into permission (name, permission, created_by, updated_by)
SELECT '客户', 'custom', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'custom');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户查询', 'custom:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'custom:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户添加', 'custom:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'custom:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户修改', 'custom:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'custom:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户删除', 'custom:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'custom:del');

# 问卷
insert into permission (name, permission, created_by, updated_by)
SELECT '问卷', 'info', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷查询', 'info:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷添加', 'info:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷编辑', 'info:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷编辑问题', 'info:edit_question', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:edit_question');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷发布', 'info:publish', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:publish');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷预览', 'info:preview', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:preview');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷删除', 'info:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:del');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷结果', 'info:result', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'info:result');