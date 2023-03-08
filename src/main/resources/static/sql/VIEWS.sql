s (48 sloc)  3.02 KB

----------------- VIEWS FOR SUBJECTS: ------------------------
	  ----------  PREREQUISITES_INFO  ----------

create view PREREQUISITES_INFO as
select b.SUBJECT_ID  as base_id, pr.SUBJECT_ID  as subject_id, pr.title as title, pr.code as code, pr.credits as credits, pr.active as active
	from  prerequisites  p join subjects b on b.subject_id = p.subject_base
	join subjects pr on pr.subject_id = p.pre_subject;


----------------- VIEWS FOR USERS: ------------------------
	  ----------  INFO_PROFESSORS  ----------

create view INFO_PROFESSORS as
select u.user_id as user_id, u.name as name , u.username as username , u.password as "password"  , u.email as email , u.gender as gender, u.active as active , p.degree as degree
from users u join professors p on u.user_id = p.professor_id;


	  ----------  INFO_STUDENTS   ----------

create view INFO_STUDENTS as
select u.user_id as user_id, u.name as name , u.username as username , u.password as "password"  , u.email as email , u.gender as gender, u.active as active, s.avg, s.status
from users u join students s on u.USER_ID = s.STUDENT_ID;

