CREATE TABLE Newsletter_types (
	Type_id serial PRIMARY KEY,
	Type varchar(255) UNIQUE NOT NULL
);

CREATE TABLE Emails (
	Email_id serial PRIMARY KEY,
	Email varchar(255) NOT NULL UNIQUE,
	Newsletter_type_id integer REFERENCES Newsletter_types(Type_id)
);

CREATE TABLE Non_members (
	Email_id integer PRIMARY KEY REFERENCES Emails(Email_id),
	Name varchar(255) NOT NULL
);

CREATE TABLE Members (
	Member_number varchar(255) PRIMARY KEY,
	Name varchar(255) NOT NULL,
	Phone char(8) NOT NULL,
	Email_id integer NOT NULL REFERENCES Emails(Email_id)
);

CREATE TABLE Employees (
	ESsn char(11) PRIMARY KEY,
	Salary real,
	Name varchar(255) NOT NULL,
	Phone_number char(8) NOT NULL,
	Email_id integer NOT NULL REFERENCES Emails(Email_id)
);

CREATE TABLE Occupied (
	ID serial PRIMARY KEY,
	Occupied_type boolean NOT NULL
);

CREATE TABLE Boat_place (
	Place_number serial PRIMARY KEY,
	Occupied_id integer REFERENCES Occupied(ID)
);

CREATE TABLE Rental_boats (
	Boat_id serial PRIMARY KEY,
	Boat_place integer REFERENCES Boat_place(Place_number),
	Price real
);

CREATE TABLE Renter (
	Email_id integer NOT NULL REFERENCES Emails(Email_id),
	Boat_id integer NOT NULL UNIQUE REFERENCES Rental_boats(Boat_id)
);

CREATE TABLE Events (
	Event_id serial PRIMARY KEY,
	Creator varchar(255) NOT NULL REFERENCES Members(Member_number),
	Responsible varchar(255) NOT NULL REFERENCES Members(Member_number),
	Name varchar(255) NOT NULL,
	Description varchar(255) NOT NULL,
	Picture varchar(255) NOT NULL
);

CREATE TABLE Event_helpers (
	Member_number varchar(255) REFERENCES Members(Member_number),
	Event_id integer REFERENCES Events(Event_id)
);

CREATE TABLE Event_participants (
	Member_number varchar(255) NOT NULL REFERENCES Members(Member_number),
	Event_id integer UNIQUE NOT NULL REFERENCES Events(Event_id)
);