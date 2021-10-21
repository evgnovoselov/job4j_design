-- Решение:

-- 1. Нужно написать SQL скрипты:
--   1) Создать структур данных в базе.
--        Таблицы.
--          Кузов. Двигатель, Коробка передач.
--   2) Создать структуру Машина. Машина не может существовать без данных из п.1.
--   3) Заполнить таблицы через insert. 
create table body (
  id serial primary key,
  name varchar(255)
);
create table engine (
  id serial primary key,
  name varchar(255)
);
create table transmission (
  id serial primary key,
  name varchar(255)
);
create table car (
  id serial primary key,
  name varchar(255),
  body_id int references body(id) not null,
  engine_id int references engine(id) not null,
  transmission_id int references transmission(id) not null
);

insert into body (name)
values ('Седан'),
('Внедорожник'),
('Купе'),
('Универсал'),
('Минивэн'),
('Хэтчбек'),
('Минифургон'),
('Кабриолет'),
('Лимузин'),
('Пикап');

insert into engine (name)
values ('Микролитражный 1,1 л'),
('Малолитражный 1,5 л'),
('Среднелитражный 3,5 л'),
('Крупнолитражный 4,5 л');

insert into transmission (name)
values ('Механическая'),
('Автоматизированная'),
('Роботизированная'),
('Вариатор');

insert into car (name, body_id, engine_id, transmission_id)
values ('Кристина', 2, 3, 1),
('Юля', 1, 2, 2),
('Алекса', 6, 3, 3),
('Анюта', 4, 3, 2),
('Вика', 8, 2, 2),
('Алла', 7, 2, 1);

-- 2. Создать SQL запросы:
-- 1) Вывести список всех машин и все привязанные к ним детали.
select c.id, c.name, c.body_id, b.name as body_name, c.engine_id, e.name as engine_name, c.transmission_id, t.name as transmission_name
from car as c
left join body as b on c.body_id = b.id
left join engine as e on c.engine_id = e.id
left join transmission as t on c.transmission_id = t.id;
-- id |   name   | body_id |  body_name  | engine_id |      engine_name      | transmission_id | transmission_name  
------+----------+---------+-------------+-----------+-----------------------+-----------------+--------------------
--  1 | Кристина |       2 | Внедорожник |         3 | Среднелитражный 3,5 л |               1 | Механическая
--  2 | Юля      |       1 | Седан       |         2 | Малолитражный 1,5 л   |               2 | Автоматизированная
--  3 | Алекса   |       6 | Хэтчбек     |         3 | Среднелитражный 3,5 л |               3 | Роботизированная
--  4 | Анюта    |       4 | Универсал   |         3 | Среднелитражный 3,5 л |               2 | Автоматизированная
--  5 | Вика     |       8 | Кабриолет   |         2 | Малолитражный 1,5 л   |               2 | Автоматизированная
--  6 | Алла     |       7 | Минифургон  |         2 | Малолитражный 1,5 л   |               1 | Механическая
--(6 строк)


-- 2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
-- body
select *
from body as b
left join car as c on c.body_id = b.id
where c.id is null;
-- id |  name   | id | name | body_id | engine_id | transmission_id 
------+---------+----+------+---------+-----------+-----------------
-- 10 | Пикап   |    |      |         |           |                
--  5 | Минивэн |    |      |         |           |                
--  3 | Купе    |    |      |         |           |                
--  9 | Лимузин |    |      |         |           |                
--(4 строки)

-- engine
select *
from engine as e
left join car as c on c.engine_id = e.id
where c.id is null;
-- id |         name          | id | name | body_id | engine_id | transmission_id 
------+-----------------------+----+------+---------+-----------+-----------------
--  4 | Крупнолитражный 4,5 л |    |      |         |           |                
--  1 | Микролитражный 1,1 л  |    |      |         |           |                
--(2 строки)


-- transmission
select *
from transmission as t
left join car as c on c.transmission_id = t.id
where c.id is null;
-- id |   name   | id | name | body_id | engine_id | transmission_id 
------+----------+----+------+---------+-----------+-----------------
--  4 | Вариатор |    |      |         |           |                
--(1 строка)

