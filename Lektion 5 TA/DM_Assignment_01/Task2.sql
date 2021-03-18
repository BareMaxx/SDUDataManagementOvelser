CREATE TABLE PERSON (
	Ssn char(11) PRIMARY KEY, -- Reasoning for Ssn: Name is not a good key because multiple persons can have the same name
	Name varchar(255) NOT NULL,
	Adress varchar(255) NOT NULL, -- Adress does not seem to use the ZIP or city enough to make them seperate.
	Age integer NOT NULL
);

CREATE TABLE PRODUCER (
	Ssn char(11) UNIQUE NOT NULL REFERENCES PERSON(Ssn)
);

CREATE TABLE ARTIST (
	Ssn char(11) UNIQUE NOT NULL REFERENCES PERSON(Ssn),
	Stage_name varchar(255) NOT NULL
);

CREATE TABLE AWARD (
	Reciever_ssn char(11) REFERENCES ARTIST(Ssn) NOT NULL,
	Name varchar(255) PRIMARY KEY, -- Name of the award, primary key in form that i.e 'Best actor 2015' will not happen twice
	Year integer NOT NULL-- Date will give a date, they want a year where I think an integer fits better
);

CREATE TABLE GENRE (
	ID serial PRIMARY KEY,
	Name varchar(255) NOT NULL
);

CREATE TABLE CREATORS (
	ID serial PRIMARY KEY,
	Producer_ssn char(11) NOT NULL REFERENCES PRODUCER(Ssn),
	Artist_ssn char(11) NOT NULL REFERENCES ARTIST(Ssn),
	Date date NOT NULL
);

CREATE TABLE ALBUM (
	ID serial PRIMARY KEY, -- Assumes that Albums has an id, this can be used to link songs to the album
	Name varchar(255) NOT NULL,
	Year integer NOT NULL, -- Date will give a date, they want a year where I think an integer fits better
	Creators integer NOT NULL REFERENCES CREATORS(ID), -- Get the artist from the CREATOR in the program.
	Genre integer NOT NULL REFERENCES GENRE(ID)
);

CREATE TABLE SONG (
	Track_number serial PRIMARY KEY, -- Multiple songs can have the same name
	Name varchar(255) NOT NULL,
	Length real NOT NULL, -- No idea how to store this
	Album integer NOT NULL REFERENCES ALBUM(ID)
);

