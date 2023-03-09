----------------- VIEWS FOR SUBJECTS: ------------------------
	  ----------  PREREQUISITES_INFO  ----------

create view PREREQUISITES_INFO as
select b.SUBJECT_ID  as base_id, pr.SUBJECT_ID  as subject_id, pr.title as title, pr.code as code, pr.credits as credits, pr.active_subject as active_subject
	from  prerequisites  p join subjects b on b.subject_id = p.subject_base
	join subjects pr on pr.subject_id = p.pre_subject;


----------------- VIEWS FOR USERS: ------------------------
	  ----------  INFO_PROFESSORS  ----------

create view INFO_PROFESSORS as
select u.user_id as user_id, u.name as name , u.username as username , u.password as "password"  , u.email as email , u.gender as gender, u.active_user as active_user , p.degree as degree
from users u join professors p on u.user_id = p.professor_id;

	  ----------  INFO_STUDENTS   ----------

create view INFO_STUDENTS as
select u.user_id as user_id, u.name as name , u.username as username , u.password as "password"  , u.email as email , u.gender as gender, u.active_user as active_user, s.avg, s.status
from users u join students s on u.USER_ID = s.STUDENT_ID;

----------------- VIEWS FOR COURSES: ------------------------

create view INFO__TOTAL_COURSES as
select c.course_id  as course_id, c.GROUP_NUMBER  as group_number, c.QUOTA  as quota, c.PROFESSOR_id as professor_id, c.subject_id  as subject, c.status_course as status_course, c.year, c.period, c.active_course as active_course,
u.USER_ID as user_id, u.name as name, u.USERNAME as username, u."password" as password, u.EMAIL as email, u.GENDER as gender, u.active_user as active_user, p.degree as degree,
s.SUBJECT_ID as SUBJECT_ID, s.title as title, s.CODE as code, s.CREDITS as CREDITS, s.active_subject as active_subject
from subjects s join courses c on c.subject_id = s.subject_id
				join users u on u.user_id = c.professor_id
				join professors p on u.user_id  = p.professor_id;


