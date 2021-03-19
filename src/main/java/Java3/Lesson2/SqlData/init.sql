create table if not exists users
(
    user      char(200) not null,
    password  char(20)  not null,
    nick_name char(20)  not null,
    constraint users_user_uindex
    unique (user)
    );

alter table users
    add primary key (user);

create table if not exists chat_history
(
    user    char(200)  not null
    primary key,
    message linestring null,
    constraint chat_history_users_user_fk
    foreign key (user) references users (user)
    );