------------------ TABLES-----------------
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";  

CREATE TABLE IF NOT EXISTS USERS (
	user_id uuid primary key DEFAULT uuid_generate_v1(),
	name VARCHAR (100) not null,
	username VARCHAR (60) unique not null,
	password VARCHAR (60) not null,
	email VARCHAR(100) unique not null,
	gender VARCHAR(1) not null,
	active BOOLEAN not null default true
);


CREATE TABLE IF NOT EXISTS STUDENTS (
	student_id uuid primary key,
	avg DECIMAL,
	status VARCHAR(10) not null,
	foreign KEY(student_id)
		references users(user_id)
);

CREATE TABLE IF NOT EXISTS  PROFESSORS (
	professor_id uuid primary key,
	degree VARCHAR(100) not null,
	foreign KEY(professor_id)
		references users(user_id)
);

CREATE TABLE IF NOT EXISTS  SUBJECTS (
	subject_id uuid primary key DEFAULT uuid_generate_v1(),
	title VARCHAR(100) unique not null,
	code VARCHAR(4) not null,
	credits INTEGER not null,
	active BOOLEAN not null default true
);


CREATE TABLE IF NOT EXISTS   COURSES (
	course_id uuid primary key DEFAULT uuid_generate_v1(),
	group_number VARCHAR(2) not null,
	quota INTEGER not null,
	professor_id uuid,
	subject_id uuid,
	status_course VARCHAR(11) not null,
	year VARCHAR(4) not null,
	period VARCHAR(2) not null,
	active BOOLEAN not null,
	foreign KEY(professor_id)
		references professors(professor_id),
	foreign KEY(subject_id)
		references Subjects(subject_id)
);

CREATE TABLE IF NOT EXISTS  RECORDS(
	record_id uuid primary key DEFAULT uuid_generate_v1(), 
	student_id uuid not null,
	course_id uuid not null,
	type VARCHAR (6) not null,
	grade DECIMAL not null,
	foreign KEY(course_id)
		references courses(course_id),
	foreign KEY(student_id)
		references students(student_id)
);


CREATE TABLE IF NOT EXISTS  PREREQUISITES(
	subject_base uuid not null,
	pre_subject uuid not null,
	PRIMARY KEY(subject_base,pre_subject),
	foreign KEY(subject_base)
		references subjects(subject_id),
	foreign KEY(pre_subject)
		references subjects(subject_id)
);