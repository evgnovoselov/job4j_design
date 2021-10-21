CREATE TABLE author (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE post (
  id SERIAL PRIMARY KEY,
  content TEXT,
  author_id INT REFERENCES author(id)
);

INSERT INTO author (name) VALUES ('Julia Yulyashina');
INSERT INTO post (content, author_id) VALUES ('Здесь было очень много текста, но он пропал.', 1);

SELECT * FROM author;
SELECT * FROM post;