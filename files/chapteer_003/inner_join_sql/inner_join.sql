create table author (
  id serial primary key,
  name varchar(255)
);

create table post (
  id serial primary key,
  name varchar(255),
  content text,
  author_id int references author(id)
);

insert into author (name) values ('Юлия'), ('Кристина'), ('Анжела');
insert into post (name, content, author_id)
values ('Как проходить собеседование', 'Много текста про собеседования', 1),
('Что быстрее C++ или Java?', 'Много текста, как все не однозначно', 2),
('Kotlin уделает Java?', 'Текст как многие фишки из котлина придут в Java', 3),
('Как устроиться программистом по Java', 'Большая история', 1);

-- 1 запрос с использование alias
select * from post inner join author as a on author_id = a.id;

-- 2 запрос с использование alias
select p.id, p.name, p.content, p.author_id, a.name from post as p inner join author as a on author_id = a.id;

-- 3 запрос с использование alias
select p.id as "Номер поста", p.name as Заголовок, p.content as Текст, a.name as Автор from post as p inner join author as a on author_id = a.id;
