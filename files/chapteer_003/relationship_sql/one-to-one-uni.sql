CREATE TABLE contact (
  id SERIAL PRIMARY KEY,
  phone VARCHAR(255),
  comment TEXT
);

CREATE TABLE people (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  contact_id INT REFERENCES contact(id) UNIQUE
);

INSERT INTO contact (phone, comment) VALUES ('+7-912-345-6789', 'ООО Ромашка');
INSERT INTO people (name, contact_id) VALUES ('Yulia Yulyashina', 1);