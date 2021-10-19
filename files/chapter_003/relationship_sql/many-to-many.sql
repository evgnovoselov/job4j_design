CREATE TABLE author (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE book (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  isbn VARCHAR(255)
);

CREATE TABLE author_book (
  id SERIAL PRIMARY KEY,
  author_id INT REFERENCES author(id),
  book_id INT REFERENCES book(id)
);

INSERT INTO author (name) VALUES ('Yulia'), ('Kristina'), ('Katya');
INSERT INTO book (name, isbn) VALUES ('Книжка по Java', '978-5-97060-674-2'),
('Java наносит ответный удар', '935-3-97951-523-5');
INSERT INTO author_book (author_id, book_id) VALUES (1, 1), (2, 1), (3, 2), (2, 2);
