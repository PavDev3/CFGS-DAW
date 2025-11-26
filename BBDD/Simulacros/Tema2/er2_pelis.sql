CREATE DATABASE er2_pelis;

CREATE TABLE movies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title_es VARCHAR(255) NOT NULL,
    title_original VARCHAR(255),
    release_year INT
);

CREATE TABLE directors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE actors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE festivals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE festival_editions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    festival_id INT NOT NULL,
    year INT NOT NULL,
    FOREIGN KEY (festival_id) REFERENCES festivals(id),
    UNIQUE (festival_id, year)
);

CREATE TABLE awards (
    id INT PRIMARY KEY AUTO_INCREMENT,
    festival_edition_id INT NOT NULL,
    movie_id INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    FOREIGN KEY (festival_edition_id) REFERENCES festival_editions(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE cinemas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    UNIQUE (name, city)
);

CREATE TABLE cinema_halls (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cinema_id INT NOT NULL,
    hall_number INT NOT NULL,
    capacity INT NOT NULL,
    FOREIGN KEY (cinema_id) REFERENCES cinemas(id)
);

CREATE TABLE screenings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT NOT NULL,
    cinema_hall_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    viewers INT,
    revenue DECIMAL(10, 2),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (cinema_hall_id) REFERENCES cinema_halls(id)
);

CREATE TABLE movie_directors (
    movie_id INT NOT NULL,
    director_id INT NOT NULL,
    PRIMARY KEY (movie_id, director_id),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (director_id) REFERENCES directors(id)
);

CREATE TABLE movie_actors (
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (actor_id) REFERENCES actors(id)
);


