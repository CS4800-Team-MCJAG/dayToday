DROP DATABASE IF EXISTS
    dayToday;

CREATE DATABASE dayToday;

use dayToday;

CREATE TABLE users(
    email varchar(50) UNIQUE,
    firstName varchar(50),
    lastName varchar(50),
    PRIMARY KEY(email)
);

CREATE TABLE events(
    eventID INT NOT NULL AUTO_INCREMENT,
    eventName varchar(100),
    startDayAndTime DateTime,
    endDayAndTime DateTime,
    alert DateTime,
    email varchar(50),
    zoomLink varChar(200),
    PRIMARY KEY(eventID),
    FOREIGN KEY (email) REFERENCES users(email)
    ON DELETE CASCADE ON UPDATE CASCADE
);