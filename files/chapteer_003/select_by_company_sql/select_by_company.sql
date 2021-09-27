CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name)
values
(1, 'Google'),
(2, 'Yandex'),
(3, 'Microsoft'),
(5, 'Sber');

insert into person (id, name, company_id)
values
(1, 'Evgeny', 5),
(2, 'Maksim', 2),
(3, 'Stas', 3),
(4, 'Olga', 3),
(5, 'Marina', 1),
(6, 'Kolya', 2),
(7, 'Nikolay', 2),
(8, 'Veronika', 5),
(9, 'Julia', 1),
(10, 'Nikita', 2),
(11, 'Kristina', 1),
(12, 'Vladimir', 5);

select * from company;
select * from person;

-- имена всех person, которые не состоят в компании с id = 5;
select * from person where not company_id = 5;

-- название компании для каждого человека.
select p.id, p.name, c.name as company
from person p left join company c
on p.company_id = c.id;

-- имена всех person, которые не состоят в компании с id = 5 и названия компаний для каждого человека.
select p.id, p.name, c.name as company
from person p left join company c
on p.company_id = c.id
where not p.company_id = 5;

-- Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
select c.id, c.name, count(p.id) count_person from company c
left join person p
on c.id = p.company_id
group by c.id
order by count_person desc
limit 1;

drop table person;
drop table company;