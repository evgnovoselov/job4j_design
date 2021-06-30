CREATE TABLE contact (
  id SERIAL PRIMARY KEY,
  phone VARCHAR(255),
  comment TEXT
);

CREATE TABLE people (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE people_contact (
  id SERIAL PRIMARY KEY,
  people_id INT REFERENCES people(id) UNIQUE,
  contact_id INT REFERENCES contact(id) UNIQUE
);

INSERT INTO contact (phone, comment) VALUES ('+7-912-345-6789', 'ООО Ромашка');
INSERT INTO people (name) VALUES ('Yulia Yulyashina');
INSERT INTO people_contact (people_id, contact_id) VALUES (1, 1);
