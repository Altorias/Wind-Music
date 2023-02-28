
-- 创建admin表
create table admin
(
    id       int(9) auto_increment,
    username varchar(25) not null,
    password varchar(50) null,
    constraint admin_pk
        primary key (id),
    constraint admin_pk
        unique (username)
);

-- 创建artist表
create table artist
(
    id          int(25) auto_increment,
    name        varchar(50)  not null,
    gender      tinyint      null,
    pic         varchar(100) null,
    birth_date  date         null,
    location    varchar(50)  null,
    description text         null,
    constraint artist_pk
        primary key (id)
);

-- 创建banner表
create table banner
(
    id        int auto_increment,
    pic       varchar(100)      not null,
    is_active tinyint default 1 not null,
    constraint banner_pk
        primary key (id)
);

-- 创建music表
create table music
(
    id          int(25) auto_increment,
    name        varchar(25)  not null,
    album       varchar(25)  null,
    create_time datetime     null,
    update_time datetime     null,
    pic         varchar(100) null,
    url         varchar(100) null,
    artist_id   int(25)   not null,
    lyrics      text      null,
    constraint music_pk
        primary key (id),
    constraint music_artist_null_fk
        foreign key (artist_id) references artist (id)
            on update set default on delete cascade
);

-- 创建歌单表
create table play_list
(
    id           int(9) auto_increment,
    title        varchar(255) not null,
    pic          varchar(255) null,
    introduction text         null,
    tag          varchar(50)  null,
    constraint play_list_pk
        primary key (id),
    constraint play_list_pk
        unique (title)
);

-- 创建Music视图
create view v_music as
select mu.*, ar.name as artist_name
from music.music mu
         left join music.artist ar on mu.artist_id = ar.id;





