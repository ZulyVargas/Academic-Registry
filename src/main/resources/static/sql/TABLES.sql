------- TABLES-------------

CREATE TABLE IF NOT EXISTS USERS (
	user_id VARCHAR(10) primary key,
	name VARCHAR (100) not null,
	username VARCHAR (60) unique not null,
	password VARCHAR (60) not null,
	email VARCHAR(100) not null,
	gender VARCHAR(1) not null,
	active BOOLEAN not null
);


CREATE TABLE IF NOT EXISTS STUDENTS (
	student_id VARCHAR(10) primary key,
	avg DECIMAL,
	status VARCHAR(9),
	foreign KEY(student_id)
		references users(user_id)
);

CREATE TABLE IF NOT EXISTS  PROFESSORS (
	professor_id VARCHAR(10) primary key,
	degree VARCHAR(100),
	foreign KEY(professor_id)
		references users(user_id)
);

CREATE TABLE IF NOT EXISTS   SUBJECTS (
	subject_id VARCHAR(10) primary key,
	title VARCHAR(100) unique not null,
	code VARCHAR(4) not null,
	credits INTEGER not null
);


CREATE TABLE IF NOT EXISTS   COURSES (
	course_id VARCHAR(10) primary key,
	group_number VARCHAR(2) unique not null,
	quota INTEGER not null,
	professor VARCHAR(10),
	subject VARCHAR(10),
	status VARCHAR(9),
	year VARCHAR(4),
	period VARCHAR(1),
	foreign KEY(professor)
		references professors(professor_id),
	foreign KEY(subject)
		references Subjects(subject_id)
);

CREATE TABLE IF NOT EXISTS  Records(
	record_id VARCHAR(10) primary key, 
	student_id VARCHAR(10),
	course_id VARCHAR(10),
	type VARCHAR (6),
	grade DECIMAL,
	foreign KEY(course_id)
		references courses(course_id),
	foreign KEY(student_id)
		references students(student_id)
);

CREATE TABLE IF NOT EXISTS  Prerequisites(
	subject_base VARCHAR(10) not null,
	pre_subject VARCHAR(10) not null,
	PRIMARY KEY(subject_base,pre_subject),
	foreign KEY(subject_base)
		references subjects(subject_id),
	foreign KEY(pre_subject)
		references subjects(subject_id)
);