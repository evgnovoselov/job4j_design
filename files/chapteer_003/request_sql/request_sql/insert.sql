insert into role (name)
values ('Администратор'), ('Пользователь');

insert into users (name, role_id)
values ('Кристина', 1), ('Юля', 2), ('Катя', 1);

insert into rules (name, rule)
values ('Изменение заявки', 'EDIT_REQ'),
('Создание заявки', 'CREATE_REQ'),
('Удаление заявки', 'DELETE_REQ');

insert into role_rules (role_id, rules_id)
values (1, 1),
(1, 2),
(1, 3),
(2, 2);

insert into category (name)
values ('Новый функционал'),
('Ошибка');

insert into state (name)
values ('Срочно'),
('Готово'),
('Ожидание');

insert into item (name, content, users_id, category_id, state_id)
values ('Поменять дизайн', 'Много текста по дизайну', 1, 1, 3),
('Поправить шапку', 'Срочно исправить ошибку в шапке', 2, 2, 2);

insert into comments (content, item_id)
values ('Большой комментарий', 1),
('Еще комментарий', 1),
('Комментарий поменьше', 2);

insert into attachs (file_path, item_id)
values ('/path/to/file.png', 1),
('/path/to/file_2.png', 1),
('/path/to/file_3.png', 2);
