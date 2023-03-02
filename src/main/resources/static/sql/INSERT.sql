--------------- USERS ----------------------

insert into USERS (user_id, name, username, password, email, gender, active) values ('6f323b65-cd24-48a9-bc53-e3e45676e619', 'Pierre Aubray', 'paubray0', 'j95p5YVCl', 'paubray0@auda.org.au', 'F', true);
insert into USERS (user_id, name, username, password, email, gender, active) values ('131e8918-730b-4739-9b9b-e91510f96fa9', 'Hayden Strephan', 'hstrephan1', '8rOmjSki', 'hstrephan1@oracle.com', 'M', true);
insert into USERS (user_id, name, username, password, email, gender, active) values ('8983514f-2e8a-4664-b616-2ea1744cb6af', 'Kerry Waslin', 'kwaslin2', 'o70kLhC4QQkO', 'kwaslin2@liveinternet.ru', 'M', true);
insert into USERS (user_id, name, username, password, email, gender, active) values ('b378f207-0f03-469c-bb6f-058779a2ad32', 'Sandi Sobieski', 'ssobieski3', 'knxGjrTj', 'ssobieski3@reverbnation.com', 'F', true);
insert into USERS (user_id, name, username, password, email, gender, active) values ('273f9c9c-c900-436b-af8a-65aead73052c', 'Amberly Pietroni', 'apietroni4', 'A8PQNZA', 'apietroni4@delicious.com', 'M', true);



--------------- STUDENTS -------------------

insert into STUDENTS (student_id, avg, status) values ('6f323b65-cd24-48a9-bc53-e3e45676e619', 3.5, 'ACTIVE');
insert into STUDENTS (student_id, avg, status) values ('131e8918-730b-4739-9b9b-e91510f96fa9', 4.0, 'ACTIVE');
insert into STUDENTS (student_id, avg, status) values ('8983514f-2e8a-4664-b616-2ea1744cb6af', 1.5, 'ACTIVE');


--------------- PROFESSORS -----------------

insert into PROFESSORS (professor_id, degree) values ('b378f207-0f03-469c-bb6f-058779a2ad32', 'Masters Degree in Mathematics');
insert into PROFESSORS (professor_id, degree) values ('273f9c9c-c900-436b-af8a-65aead73052c', 'Civil Engineer ');



--------------- SUBJECTS -------------------

insert into SUBJECTS (title, code, credits ) values ( 'CALCULO DIFERENCIAL', 'CALD', '4');
insert into SUBJECTS (title, code, credits ) values ( 'CALCULO INTEGRAL', 'CALI', '4');
insert into SUBJECTS (title, code, credits ) values ( 'BIOLOGIA', 'BIOL', '3');
insert into SUBJECTS (title, code, credits ) values ( 'PRE CALCULO', 'PCAL', '3');



--------------- COURSES --------------------

insert into COURSES (course_id, group_number, quota, professor, subject, status_course, year, period, active) values ('cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 1, 20, 'b378f207-0f03-469c-bb6f-058779a2ad32', '7ce15628-b60a-11ed-b86e-0242ac110002', 'IN_PROGRESS', '2023', 'I',true);
insert into COURSES (course_id, group_number, quota, professor, subject, status_course, year, period, active) values ('38ef1de2-e0c6-404c-8cb0-b3e048a01fc0', 2, 25, 'b378f207-0f03-469c-bb6f-058779a2ad32', '7ce15628-b60a-11ed-b86e-0242ac110002', 'IN_PROGRESS', '2023', 'I',true );
insert into COURSES (course_id, group_number, quota, professor, subject, status_course, year, period, active) values ('24abb2bd-7e2b-4cc4-8b6c-dd292d3373a0', 2, 15, '273f9c9c-c900-436b-af8a-65aead73052c', '7ce16910-b60a-11ed-b86e-0242ac110002', 'IN_PROGRESS', '2023', 'I',true );


--------------- RECORDS --------------------
	
insert into RECORDS (record_id, student_id, course_id, type, grade) values ('73f434dc-0e37-4ec2-9b36-7744fcc15f96', '6f323b65-cd24-48a9-bc53-e3e45676e619', 'cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 'FIRST', 3.9 );
insert into RECORDS (record_id, student_id, course_id, type, grade) values ('a23f4dd9-ae58-4391-bd03-1545d5d28d79', '131e8918-730b-4739-9b9b-e91510f96fa9', 'cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 'FIRST', 3.1 );
insert into RECORDS (record_id, student_id, course_id, type, grade) values ('c1fb643b-3196-443a-abcb-95800531374a', '8983514f-2e8a-4664-b616-2ea1744cb6af', 'cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 'FIRST', 4.1 );


-------------- PREREQUISITES ---------------
SELECT * FROM SUBJECTS;

insert into PREREQUISITES (subject_base, pre_subject) values ('7ce15628-b60a-11ed-b86e-0242ac110002','7ce16910-b60a-11ed-b86e-0242ac110002');
insert into PREREQUISITES (subject_base, pre_subject) values ('7ce16488-b60a-11ed-b86e-0242ac110002','7ce15628-b60a-11ed-b86e-0242ac110002');

