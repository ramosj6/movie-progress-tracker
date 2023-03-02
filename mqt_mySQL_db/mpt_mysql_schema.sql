------------------------------------------------------------------------------------
-- Project     : Movie Progress Tracker
------------------------------------------------------------------------------------
-- initialize the database
------------------------------------------------------------------------------------

DROP DATABASE IF EXISTS mpt;

CREATE DATABASE mpt;

USE mpt;

------------------------------------------------------------------------------------
-- execute the following statements to create tables
------------------------------------------------------------------------------------
-- 
CREATE TABLE user
(
	user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(15),
    email VARCHAR(30),
    username VARCHAR(20),
    password VARCHAR(20)

);


CREATE TABLE movies
(
	movie_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    genre VARCHAR(50),
    length int,
    film_studios VARCHAR(20)


);


CREATE TABLE user_movie
(
	user_id int,
    movie_id int,
    status VARCHAR(5),
    primary key(user_id, movie_id),
    foreign key(user_id) references user(user_id),
    foreign key(movie_id) references movies(movie_id)

);
