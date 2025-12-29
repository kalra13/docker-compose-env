create table if not exists countries (
    id int,
    name varchar(36),
    primary key (id));


insert into countries (id, name)
values (1, 'Canada'), (2, 'America'), (3, 'Mexico');