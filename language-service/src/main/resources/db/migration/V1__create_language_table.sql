create table if not exists language (
                                         name varchar(36),
                                         country varchar(36),
                                         primary key (name,country));


insert into language (name, country)
values ('French', 'Canada'),
       ('English', 'Canada'),
       ('English', 'America');