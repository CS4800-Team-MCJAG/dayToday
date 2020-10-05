DROP DATABASE IF EXISTS
    dayToday;

CREATE DATABASE dayToday;

use dayToday;

CREATE TABLE users(
    email varchar(50) NOT NULL UNIQUE,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    PRIMARY KEY(email)
);

CREATE TABLE events(
    eventID int NOT NULL UNIQUE AUTO_INCREMENT,
    eventName varchar(100),
    startDayAndTime DateTime,
    endDayAndTime DateTime,
    alert time,
    email varchar(50) NOT NULL,
    PRIMARY KEY(eventID),
    FOREIGN KEY (email) REFERENCES users(email)
    ON DELETE CASCADE ON UPDATE CASCADE
);