create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Device 1', 1000.01);
insert into devices (name, price) values ('Device 2', 2000.02);
insert into devices (name, price) values ('Device 3', 3000.03);
insert into devices (name, price) values ('Device 4', 4000.04);
insert into devices (name, price) values ('Device 5', 15000.15);

insert into people (name) values ('Юля');
insert into people (name) values ('Кристина');
insert into people (name) values ('Настя');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (2, 1);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (4, 1);

insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (3, 2);
insert into devices_people (device_id, people_id) values (4, 2);

insert into devices_people (device_id, people_id) values (1, 3);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (4, 3);
insert into devices_people (device_id, people_id) values (5, 3);

-- Решение:

-- 3. Используя агрегатные функции вывести среднюю цену устройств.
select avg(price) from devices;
--    avg   
-- ---------
--  5000.05
-- (1 строка)

-- 4. Используя группировку вывести для каждого человека среднюю цену его устройств.
select p.name, avg(d.price)
from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name;
--    name   |   avg    
-- ----------+----------
--  Настя    |  5000.05
--  Юля      | 2500.025
--  Кристина | 2500.025
-- (3 строки)

-- 5. Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.
select p.name, avg(d.price)
from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
--  name  |   avg   
-- -------+---------
--  Настя | 5000.05
-- (1 строка)
