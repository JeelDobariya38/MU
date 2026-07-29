--- P1

CREATE DATABASE IF NOT EXISTS employee;
USE employee;

CREATE TABLE IF NOT EXISTS emp (
    id INTEGER AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50), 
    age INTEGER, 
    city VARCHAR(50)
);

INSERT INTO emp (name, age, city) VALUES 
    ('John Doe', 28, 'Gandhinagar'),
    ('Smith', 14, 'Rajkot'),
    ('Alex', 25, 'USA'),
    ('Emily', 30, 'Ahemdabad'),
    ('Raj', 23, 'Delhi'),
    ('Vraj', 29, 'Rajkot'),
    ('David Lee', 15, 'NYC');

--- P2

DELIMITER //

CREATE PROCEDURE get_city(
    IN p_empid INT,
    OUT p_city VARCHAR(50)
)
BEGIN
    SELECT city INTO p_city 
    FROM emp 
    WHERE id = p_empid;
END //

DELIMITER ;
