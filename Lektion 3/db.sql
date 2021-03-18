CREATE TABLE Designation (
	ID serial UNIQUE NOT NULL,
	Designation_name varchar(255) UNIQUE NOT NULL,
	Charges_per_hour integer NOT NULL,
	PRIMARY KEY (ID)
);
CREATE TABLE Doctor (
	Doctor_Nr varchar(255) UNIQUE NOT NULL,
	Doctor_Name varchar(255) NOT NULL,
	Doctor_Phone char(8),
	Designation_ID integer NOT NULL,
	Doctor_Room integer,
	PRIMARY KEY (Doctor_Nr),
	FOREIGN KEY (Designation_ID) REFERENCES Designation(ID)
);
CREATE TABLE Doctor_rooms (
	Doctor_room_ID serial UNIQUE NOT NULL,
	Doctor_Room_Adress varchar(255) UNIQUE NOT NULL,
	PRIMARY KEY (Doctor_room_ID)
);
CREATE TABLE Department (
	Department_ID serial UNIQUE NOT NULL,
	Department_Name varchar(255) UNIQUE NOT NULL,
	PRIMARY KEY (Department_ID)
);
CREATE TABLE Working_Department (
	ID serial UNIQUE NOT NULL,
	Department_ID integer NOT NULL,
	Doctor_Nr varchar(255) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (Department_id) REFERENCES Department(Department_ID),
	FOREIGN KEY (Doctor_Nr) REFERENCES Doctor(Doctor_Nr)
);
CREATE TABLE Patients (
	Patient_Number varchar(255) UNIQUE NOT NULL,
	CPR_Number char(11) UNIQUE NOT NULL,
	Patient_Name varchar(255) NOT NULL,
	Patient_Phone char(8),
	PRIMARY KEY (Patient_Number)
);
CREATE TABLE Patient_Room_Types (
	ID serial UNIQUE NOT NULL,
	Room_Type varchar UNIQUE NOT NULL,
	PRIMARY KEY (ID)
);
CREATE TABLE Patient_Rooms (
	Room_Number varchar(255) UNIQUE NOT NULL,
	Room_Type integer NOT NULL,
	PRIMARY KEY (Room_Number),
	FOREIGN KEY (Room_Type) REFERENCES Patient_Room_Types(ID)
	
);
CREATE TABLE Beds (
	Bed_Number varchar(255) UNIQUE NOT NULL,
	Room_Number varchar NOT NULL,
	PRIMARY KEY (Bed_Number),
	FOREIGN KEY (Room_Number) REFERENCES Patient_Rooms(Room_Number)
);
CREATE TABLE Appointments (
	Patient_Number varchar(255) NOT NULL,
	Doctor_Number varchar(255) NOT NULL,
	Bed varchar(255),
	FOREIGN KEY (Patient_Number) REFERENCES Patients(Patient_Number),
	FOREIGN KEY (Doctor_Number) REFERENCES Doctor(Doctor_Nr),
	FOREIGN KEY (Bed) REFERENCES Beds(Bed_Number)
);

ALTER TABLE Designation ALTER COLUMN charges_per_hour TYPE real;

INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P1', '190582-1113', 'Jan', '98769876');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P5', '300175-2359', 'Peter', '87658765');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P7', '041298-1257', 'Jens', '76547654');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P4', '051165-9863', 'Ole', '65436543');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P9', '260792-1050', 'Anna', '54325432');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P10', '150893-1151', 'Dennis', '43214321');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P12', '010211-7853', 'Ahmed', '32103210');
INSERT INTO Patients (Patient_Number, CPR_Number, Patient_Name, Patient_Phone) VALUES ('P13', '051285-8072', 'Annika', '21092109');

INSERT INTO Patient_room_types (room_type) VALUES ('Normal');
INSERT INTO Patient_room_types (room_type) VALUES ('Two Bed');
INSERT INTO Patient_room_types (room_type) VALUES ('Special');

INSERT INTO patient_rooms (room_number, room_type) VALUES ('R2', 1);
INSERT INTO patient_rooms (room_number, room_type) VALUES ('R4', 2);
INSERT INTO patient_rooms (room_number, room_type) VALUES ('R5', 3);
INSERT INTO patient_rooms (room_number, room_type) VALUES ('R6', 3);

INSERT INTO beds (Bed_number, Room_number) VALUES ('B1', 'R2');
INSERT INTO beds (Bed_number, Room_number) VALUES ('B5', 'R4');
INSERT INTO beds (Bed_number, Room_number) VALUES ('B7', 'R4');
INSERT INTO beds (Bed_number, Room_number) VALUES ('B8', 'R5');
INSERT INTO beds (Bed_number, Room_number) VALUES ('B9', 'R6');

INSERT INTO department (Department_name) VALUES ('Neurology');
INSERT INTO department (Department_name) VALUES ('Orthopedic');
INSERT INTO department (Department_name) VALUES ('ENT');
INSERT INTO department (Department_name) VALUES ('Skin');

INSERT INTO designation (Designation_name, Charges_per_hour) VALUES ('Professor', 5000);
INSERT INTO designation (Designation_name, Charges_per_hour) VALUES ('Assistant Professor', 3000);

INSERT INTO doctor_rooms (doctor_room_adress) VALUES ('U45');
INSERT INTO doctor_rooms (doctor_room_adress) VALUES ('U32');
INSERT INTO doctor_rooms (doctor_room_adress) VALUES ('U186');
INSERT INTO doctor_rooms (doctor_room_adress) VALUES ('U150');

INSERT INTO doctor (Doctor_Nr, Doctor_Name, Doctor_Phone, Designation_ID, Doctor_Room) VALUES
('D1', 'Dr. Peterson', '12341234', 1, 1);
INSERT INTO doctor (Doctor_Nr, Doctor_Name, Doctor_Phone, Designation_ID, Doctor_Room) VALUES
('D2', 'Dr. Jensen', '24352435', 1, 2);
INSERT INTO doctor (Doctor_Nr, Doctor_Name, Doctor_Phone, Designation_ID, Doctor_Room) VALUES
('D4', 'Dr. Poetch', '34563456', 2, 3);
INSERT INTO doctor (Doctor_Nr, Doctor_Name, Doctor_Phone, Designation_ID, Doctor_Room) VALUES
('D5', 'Dr. Neurenheim', '45674567', 2, 4);

INSERT INTO Working_department (Department_ID, doctor_nr) VALUES (1, 'D1');
INSERT INTO Working_department (Department_ID, doctor_nr) VALUES (2, 'D2');
INSERT INTO Working_department (Department_ID, doctor_nr) VALUES (3, 'D4');
INSERT INTO Working_department (Department_ID, doctor_nr) VALUES (1, 'D4');
INSERT INTO Working_department (Department_ID, doctor_nr) VALUES (4, 'D5');
INSERT INTO Working_department (Department_ID, doctor_nr) VALUES (2, 'D5');

INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P1', 'D1', 'B1');
INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P1', 'D4', 'B8');
INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P5', 'D1', 'B1');
INSERT INTO appointments (Patient_Number, Doctor_Number) VALUES ('P7', 'D1');
INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P7', 'D2', 'B5');
INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P4', 'D2', 'B1');
INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P9', 'D2', 'B7');
INSERT INTO appointments (Patient_Number, Doctor_Number) VALUES ('P10', 'D4');
INSERT INTO appointments (Patient_Number, Doctor_Number) VALUES ('P12', 'D5');
INSERT INTO appointments (Patient_Number, Doctor_Number, bed) VALUES ('P13', 'D5', 'B9');
