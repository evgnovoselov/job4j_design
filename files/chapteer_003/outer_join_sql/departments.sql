-- Решение:
-- 1. Создать таблицы и заполнить их начальными данными
create table departments (
  id serial primary key,
  name varchar(255)
);

create table emploees (
  id serial primary key,
  name varchar(255),
  department_id int references departments(id)
);

insert into departments (name)
values ('Разработка'), ('Бухгалтерия'), ('Столовая');

insert into emploees (name, department_id)
values ('Иван', 1),
('Олег', 1),
('Андрей', 1),
('Кристина', 2),
('Юля', 2);

-- 2. Выполнить запросы с left, rigth, full, cross соединениями
-- left 1 outer departments
select *
from departments as d
left join emploees as e on e.department_id = d.id;
-- right 1 outer departments
select *
from emploees as e
right join departments as d on e.department_id = d.id;
-- id |    name     | id |   name   | department_id 
------+-------------+----+----------+---------------
--  1 | Разработка  |  1 | Иван     |             1
--  1 | Разработка  |  2 | Олег     |             1
--  1 | Разработка  |  3 | Андрей   |             1
--  2 | Бухгалтерия |  4 | Кристина |             2
--  2 | Бухгалтерия |  5 | Юля      |             2
--  3 | Столовая    |    |          |              
--(6 строк)

-- left 2 outer emploees
select *
from emploees as e
left join departments as d on e.department_id = d.id;
-- right 2 outer emploees
select *
from departments as d
right join emploees as e on e.department_id = d.id;
-- id |   name   | department_id | id |    name     
------+----------+---------------+----+-------------
--  1 | Иван     |             1 |  1 | Разработка
--  2 | Олег     |             1 |  1 | Разработка
--  3 | Андрей   |             1 |  1 | Разработка
--  4 | Кристина |             2 |  2 | Бухгалтерия
--  5 | Юля      |             2 |  2 | Бухгалтерия
--(5 строк)

-- full
select *
from departments as d
full join emploees as e on e.department_id = d.id;
-- id |    name     | id |   name   | department_id 
------+-------------+----+----------+---------------
--  1 | Разработка  |  1 | Иван     |             1
--  1 | Разработка  |  2 | Олег     |             1
--  1 | Разработка  |  3 | Андрей   |             1
--  2 | Бухгалтерия |  4 | Кристина |             2
--  2 | Бухгалтерия |  5 | Юля      |             2
--  3 | Столовая    |    |          |              
--(6 строк)

-- cross
select *
from emploees as e
cross join departments as d;
-- id |   name   | department_id | id |    name     
------+----------+---------------+----+-------------
--  1 | Иван     |             1 |  1 | Разработка
--  1 | Иван     |             1 |  2 | Бухгалтерия
--  1 | Иван     |             1 |  3 | Столовая
--  2 | Олег     |             1 |  1 | Разработка
--  2 | Олег     |             1 |  2 | Бухгалтерия
--  2 | Олег     |             1 |  3 | Столовая
--  3 | Андрей   |             1 |  1 | Разработка
--  3 | Андрей   |             1 |  2 | Бухгалтерия
--  3 | Андрей   |             1 |  3 | Столовая
--  4 | Кристина |             2 |  1 | Разработка
--  4 | Кристина |             2 |  2 | Бухгалтерия
--  4 | Кристина |             2 |  3 | Столовая
--  5 | Юля      |             2 |  1 | Разработка
--  5 | Юля      |             2 |  2 | Бухгалтерия
--  5 | Юля      |             2 |  3 | Столовая
--(15 строк)


-- 3. Используя left join найти департаменты, у которых нет работников
select *
from departments as d
left join emploees as e on e.department_id = d.id
where e.id is null;
-- id |   name   | id | name | department_id 
------+----------+----+------+---------------
--  3 | Столовая |    |      |              
--(1 строка)


-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
-- left 1 outer departments
select *
from departments as d
left join emploees as e on e.department_id = d.id;
-- right 1 outer departments
select *
from emploees as e
right join departments as d on e.department_id = d.id;
-- id |    name     | id |   name   | department_id 
------+-------------+----+----------+---------------
--  1 | Разработка  |  1 | Иван     |             1
--  1 | Разработка  |  2 | Олег     |             1
--  1 | Разработка  |  3 | Андрей   |             1
--  2 | Бухгалтерия |  4 | Кристина |             2
--  2 | Бухгалтерия |  5 | Юля      |             2
--  3 | Столовая    |    |          |              
--(6 строк)

-- left 2 outer emploees
select *
from emploees as e
left join departments as d on e.department_id = d.id;
-- right 2 outer emploees
select *
from departments as d
right join emploees as e on e.department_id = d.id;
-- id |   name   | department_id | id |    name     
------+----------+---------------+----+-------------
--  1 | Иван     |             1 |  1 | Разработка
--  2 | Олег     |             1 |  1 | Разработка
--  3 | Андрей   |             1 |  1 | Разработка
--  4 | Кристина |             2 |  2 | Бухгалтерия
--  5 | Юля      |             2 |  2 | Бухгалтерия
--(5 строк)

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens (
  id serial primary key,
  name varchar(255),
  gender varchar(255)
);

insert into teens (name, gender)
values ('Алиса', 'female'),
('Юля', 'female'),
('Кристина', 'female'),
('Андрей', 'male'),
('Сергей', 'male');

select *
from teens as t1
cross join teens as t2
where t1.gender = 'female' and t1.gender != t2.gender;
-- id |   name   | gender | id |  name  | gender 
------+----------+--------+----+--------+--------
--  1 | Алиса    | female |  4 | Андрей | male
--  1 | Алиса    | female |  5 | Сергей | male
--  2 | Юля      | female |  4 | Андрей | male
--  2 | Юля      | female |  5 | Сергей | male
--  3 | Кристина | female |  4 | Андрей | male
--  3 | Кристина | female |  5 | Сергей | male
--(6 строк)
