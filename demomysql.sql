drop table if exists t1;
drop table if exists t2;

create table t1
(
    c1 varchar(1) not null,
    c2 int
);
create table t2
(
    c1 varchar(1) not null,
    c2 int
);

insert into t1(c1, c2)
values ('a', 1),
       ('a', 2),
       ('a', 3),
       ('b', 1),
       ('b', 2),
       ('b', 3);

insert into t2(c1, c2)
values ('c', 1),
       ('c', 2),
       ('c', 3),
       ('d', 1),
       ('d', 2),
       ('d', 3);

commit;

