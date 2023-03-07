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

insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'ed4f110a-ba03-11ed-afa1-0242ac120002','CALCULO DIFERENCIAL', 'CALD', 4, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '06ad6480-ba04-11ed-afa1-0242ac120002','CALCULO INTEGRAL', 'CALI', 4, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '1403f93c-ba04-11ed-afa1-0242ac120002','BIOLOGIA', 'BIOL', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '1f3efb1c-ba04-11ed-afa1-0242ac120002','PRE CALCULO', 'PCAL', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'f90bbcb9-531d-4259-9d52-e92f29a961e5','FUNDAMENTOS DE MECANICA', 'FMEC', 4, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '55d02807-635f-4408-b18f-ee92ef0f7de4','FUNDAMENTOS DE COMUNICACION', 'FCOM', 2, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'cb7e06de-bd29-11ed-afa1-0242ac120002','QUIMICA I', 'QUIM', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '74efce37-2775-4016-b357-80a3ddaa85f0','MATEMATICA FINANCIERA', 'MATF', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'ccb75709-a567-4934-85bd-4f733092784e','OPTIMIZACION LINEAL', 'OPTL', 4, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '9d1d7edd-46e6-4001-b277-efdd4b513042','COSTOS Y PRESUPUESTOS', 'CYPR', 4, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '6501acbf-ce9e-4b07-a87b-12b74d62c429','SEÑALES DIGITALES', 'SDIG', 4, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '3dfb64cb-c01c-416b-9a36-8d6df97d8933','SEGURIDAD EN LA INDUSTRIA', 'SIND', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'cd11e55a-e064-42d1-bf25-d0363b5715d9','ANALISIS NUMERICO', 'ANUM', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'ee2bf736-7f56-4d3d-a8db-2e44a5767325','EXPRESION GRAFICA', 'EGRA', 3, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( '30f70acc-3b66-4bed-90f3-38e614c055dd','CINE Y LITERATURA', 'CYLI', 2, true);
insert into SUBJECTS (subject_id, title, code, credits, active ) values ( 'bdc6e175-aa1e-4d78-8078-bfb6a80bba7a','HISTORIA DE COLOMBIA', 'HDCL', 1, true);

--------------- COURSES --------------------

insert into COURSES (course_id, group_number, quota, professor_id, subject_id, status_course, year, period, active) values ('cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 1, 20, 'b378f207-0f03-469c-bb6f-058779a2ad32', '1f3efb1c-ba04-11ed-afa1-0242ac120002', 'IN_PROGRESS', '2023', 'I',true);
insert into COURSES (course_id, group_number, quota, professor_id, subject_id, status_course, year, period, active) values ('38ef1de2-e0c6-404c-8cb0-b3e048a01fc0', 2, 25, 'b378f207-0f03-469c-bb6f-058779a2ad32', '1f3efb1c-ba04-11ed-afa1-0242ac120002', 'IN_PROGRESS', '2023', 'I',true );
insert into COURSES (course_id, group_number, quota, professor_id, subject_id, status_course, year, period, active) values ('24abb2bd-7e2b-4cc4-8b6c-dd292d3373a0', 2, 15, '273f9c9c-c900-436b-af8a-65aead73052c', '06ad6480-ba04-11ed-afa1-0242ac120002', 'IN_PROGRESS', '2023', 'I',true );


--------------- RECORDS --------------------
	
insert into RECORDS (record_id, student_id, course_id, type, grade) values ('73f434dc-0e37-4ec2-9b36-7744fcc15f96', '6f323b65-cd24-48a9-bc53-e3e45676e619', 'cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 'FIRST', 3.9 );
insert into RECORDS (record_id, student_id, course_id, type, grade) values ('a23f4dd9-ae58-4391-bd03-1545d5d28d79', '131e8918-730b-4739-9b9b-e91510f96fa9', 'cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 'FIRST', 3.1 );
insert into RECORDS (record_id, student_id, course_id, type, grade) values ('c1fb643b-3196-443a-abcb-95800531374a', '8983514f-2e8a-4664-b616-2ea1744cb6af', 'cfe96f22-5c2c-406a-b458-3831aaa6d3c9', 'FIRST', 4.1 );


-------------- PREREQUISITES ---------------

insert into PREREQUISITES (subject_base, pre_subject) values ('06ad6480-ba04-11ed-afa1-0242ac120002','ed4f110a-ba03-11ed-afa1-0242ac120002');
insert into PREREQUISITES (subject_base, pre_subject) values ('ed4f110a-ba03-11ed-afa1-0242ac120002','1f3efb1c-ba04-11ed-afa1-0242ac120002');
	