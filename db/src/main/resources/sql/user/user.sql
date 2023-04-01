create table if not exists user(
    id                int auto_increment,
    username          varchar(1000)               not null,
    password          varchar(1000)                not null,
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

# passwordEncoder.encode(prefix + "123456");
insert into user (username, password, created_by, updated_by)
SELECT 'admin', '$2a$10$lp50.ImfhBS1LuHHSiLyDu4I.1H9yh58JZ47GPB4GaTw/BtaOGJlq', 1, 1
WHERE NOT EXISTS
    (SELECT id FROM user WHERE username = 'admin');