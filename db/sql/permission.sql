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

insert into permission (name, permission, created_by, updated_by)
SELECT '系统设置', 'setting', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting');

# 权限
insert into permission (name, permission, created_by, updated_by)
SELECT '权限', 'setting:permission', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:permission');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限查询', 'setting:permission:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:permission:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限添加', 'setting:permission:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:permission:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限修改', 'setting:permission:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:permission:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '权限删除', 'setting:permission:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:permission:del');

# 用户
insert into permission (name, permission, created_by, updated_by)
SELECT '用户', 'setting:user', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:user');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户查询', 'setting:user:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:user:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户添加', 'setting:user:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:user:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户修改', 'setting:user:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:user:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '用户删除', 'setting:user:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:user:del');

# 角色
insert into permission (name, permission, created_by, updated_by)
SELECT '角色', 'setting:role', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:role');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色查询', 'setting:role:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:role:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色添加', 'setting:role:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:role:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色修改', 'setting:role:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:role:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '角色删除', 'setting:role:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'setting:role:del');


insert into permission (name, permission, created_by, updated_by)
SELECT '客户关系', 'relation', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation');

# 标签
insert into permission (name, permission, created_by, updated_by)
SELECT '标签', 'relation:tag', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:tag');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签查询', 'relation:tag:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:tag:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签添加', 'relation:tag:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:tag:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签修改', 'relation:tag:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:tag:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '标签删除', 'relation:tag:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:tag:del');

# 客户
insert into permission (name, permission, created_by, updated_by)
SELECT '客户', 'relation:custom', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:custom');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户查询', 'relation:custom:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:custom:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户添加', 'relation:custom:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:custom:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户修改', 'relation:custom:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:custom:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '客户删除', 'relation:custom:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'relation:custom:del');

insert into permission (name, permission, created_by, updated_by)
SELECT '活动配置', 'activity', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity');

# 问卷
insert into permission (name, permission, created_by, updated_by)
SELECT '问卷', 'activity:info', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷查询', 'activity:info:query', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:query');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷添加', 'activity:info:add', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:add');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷编辑', 'activity:info:edit', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:edit');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷编辑问题', 'activity:info:edit_question', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:edit_question');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷发布', 'activity:info:publish', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:publish');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷预览', 'activity:info:preview', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:preview');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷删除', 'activity:info:del', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:del');

insert into permission (name, permission, created_by, updated_by)
SELECT '问卷结果', 'activity:info:result', 1, 1 WHERE NOT EXISTS (SELECT id FROM permission WHERE permission = 'activity:info:result');