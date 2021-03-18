CREATE TABLE BODYPARTS (
	ID serial PRIMARY KEY,
	Name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE WORKOUT_TYPES ( 
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Body_parts integer NOT NULL REFERENCES BODYPARTS(ID)
);

CREATE TABLE EQUIPMENT (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL
);

CREATE TABLE INSTRUCTOR (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL
);

CREATE TABLE Course (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Date timestamp NOT NULL,
	Duration interval NOT NULL,
	Workout_type integer NOT NULL REFERENCES WORKOUT_TYPES(ID),
	Equipment integer NOT NULL REFERENCES EQUIPMENT(ID),
	Instructor integer NOT NULL REFERENCES INSTRUCTOR(ID),
	Center integer NOT NULL REFERENCES CENTER(ID)
);

CREATE TABLE MEMBERSHIPS (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Price real NOT NULL
);

CREATE TABLE ATTENDEES (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Membership integer NOT NULL REFERENCES MEMBERSHIPS(ID),
	Adress varchar(255) NOT NULL
);

CREATE TABLE CENTER_LOCATIONS (
	ID serial PRIMARY KEY,
	Adress varchar(255) UNIQUE NOT NULL
);

CREATE TABLE CENTER_DIRECTORS (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL
);

CREATE TABLE CENTER (
	ID serial PRIMARY KEY,
	Adress integer NOT NULL REFERENCES CENTER_LOCATIONS(ID),
	Director integer NOT NULL REFERENCES CENTER_DIRECTORS(ID)
);

INSERT INTO INSTRUCTOR (Name) VALUES ('Sally');
INSERT INTO INSTRUCTOR (Name) VALUES ('Chris');

INSERT INTO CENTER_DIRECTORS (Name) VALUES ('Benjamin Vogel');
INSERT INTO CENTER_DIRECTORS (Name) VALUES ('Aileen Adler');

INSERT INTO CENTER_LOCATIONS (Adress) VALUES ('Karl Marx Alle 23');
INSERT INTO CENTER_LOCATIONS (Adress) VALUES ('Banegårds vejen 2');

INSERT INTO CENTER (Adress, Director) VALUES (1, 1);
INSERT INTO CENTER (Adress, Director) VALUES (2, 2);

INSERT INTO MEMBERSHIPS (Name, Price) VALUES ('Platin', 200);
INSERT INTO MEMBERSHIPS (Name, Price) VALUES ('Silver', 150);
INSERT INTO MEMBERSHIPS (Name, Price) VALUES ('Gold', 350);

INSERT INTO EQUIPMENT (Name) VALUES ('None');
INSERT INTO EQUIPMENT (Name) VALUES ('Weights');
INSERT INTO EQUIPMENT (Name) VALUES ('Yoga matt');

INSERT INTO BODYPARTS (Name) VALUES ('Abs');
INSERT INTO BODYPARTS (Name) VALUES ('Full Body');

INSERT INTO WORKOUT_TYPES (Name, Body_parts) VALUES ('Cardio', 1);
INSERT INTO WORKOUT_TYPES (Name, Body_parts) VALUES ('HIIT', 2);
INSERT INTO WORKOUT_TYPES (Name, Body_parts) VALUES ('Pilates', 2);

INSERT INTO ATTENDEES (Name, Membership, adress) VALUES ('Max Mustermann', 1, 'Juri Gagarin Ring 3');
INSERT INTO ATTENDEES (Name, Membership, adress) VALUES ('Ida Grøn', 3, 'Krokus alle 27');
INSERT INTO ATTENDEES (Name, Membership, adress) VALUES ('Mette Mohr', 2, 'Stenløsevej 203');

INSERT INTO COURSE (Name, date, duration, workout_type, equipment, instructor, center) 
VALUES ('Ultimate Abs', '7 pm 21.07.2021', '90 minutes', 1, 1, 1, 1);
