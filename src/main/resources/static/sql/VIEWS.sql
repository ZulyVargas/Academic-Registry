<<<<<<< HEAD
-------------- STUDENTS INFO ----------------------
=======
----------------- VIEWS FOR SUBJECTS: ------------------------
	  ----------  PREREQUISITES_INFO  ----------

create view PREREQUISITES_INFO as
select b.SUBJECT_ID  as base_id, pr.SUBJECT_ID  as subject_id, pr.title as title, pr.code as code, pr.credits as credits, pr.active as active
	from  prerequisites  p join subjects b on b.subject_id = p.subject_base
	join subjects pr on pr.subject_id = p.pre_subject;


--------------- STUDENTS INFO ----------------------
>>>>>>> feature/subjects

create view INFO_STUDENTS as
select u.user_id as user_id, u.name as name , u.username as username , u.password as "password"  , u.email as email , u.gender as gender, u.active as active, s.avg, s.status
from users u join students s on u.USER_ID = s.STUDENT_ID;

select * from INFO_STUDENTS;

--------------  INFO PROFESSORS --------------------

create view INFO_PROFESSORS as
select u.user_id as user_id, u.name as name , u.username as username , u.password as "password"  , u.email as email , u.gender as gender, u.active as active , p.degree as degree
from users u join professors p on u.user_id = p.professor_id;

select * from INFO_PROFESSORS;


-------------- INFO COURSES: GROUP, SUBJECT, PROFESSOR, YEAR, PERIOD (ALL data from course, subject, professor)------------------------
create view INFO__TOTAL_COURSES as
select c.course_id  as course_id, c.GROUP_NUMBER  as group_number, c.QUOTA  as quota, c.PROFESSOR_id as professor_id, c.subject_id  as subject, c.status_course as status_course, c.year, c.period, c.active  as activec, 
u.USER_ID as user_id, u.name as name, u.USERNAME as username, u."password" as password, u.EMAIL as email, u.GENDER as gender, u.ACTIVE as active, 
s.SUBJECT_ID as SUBJECT_ID, s.title as title, s.CODE as code, s.CREDITS as CREDITS,
p.degree as degree
from subjects s join courses c on c.subject_id = s.subject_id
				join users u on u.user_id = c.professor_id
				join professors p on u.user_id  = p.professor_id;
			
drop view INFO__TOTAL_COURSES;

SELECT * FROM INFO__TOTAL_COURSES;

-------------- INFO COURSES-STUDENTS: GROUP, CODE SUBJECT, STUDENT, EMAIL STUDENT

create view ENROLLED_COURSE  as
select c.group_number, s.code, u.name as student, u.email 
from users u join records r on u.user_id = r.student_id
			 join courses c on c.course_id = r.course_id
			 join subjects s on c.subject = s.subject_id;
			
select * from ENROLLED_COURSE;

-------------- GRADES_COURSE ------------

create view GRADES_COURSE  as
select c.group_number, s.code, u.name as student, u.email, r.grade, r.type 
from users u join records r on u.user_id = r.student_id
			 join courses c on c.course_id = r.course_id
			 join subjects s on c.subject = s.subject_id;


select * from GRADES_COURSE;

------------ PREREQUISITES_SUBJECT ----------

create view PREREQUISITES_SUBJECT as
select b.SUBJECT_ID  as subject_id, b.title as subject, pr.SUBJECT_ID as prerrequisite_id, pr.title as prerequisite 
	from  prerequisites  p join subjects b on b.subject_id = p.subject_base
	join subjects pr on pr.subject_id = p.pre_subject;
  

SELECT * FROM PREREQUISITES_SUBJECT;


  

SELECT * FROM PREREQUISITES_INFO;