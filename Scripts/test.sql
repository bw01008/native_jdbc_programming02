grant 
  all 
  on native_jdbc2.* 
  to 'user_native_jdbc2'@'localhost' 
identified by 'rootroot';


select * from title;
select * from department;
select * from employee;

select tno, tname from title where tno = 1;
insert into title values(6, '인턴');
delete from title where tno = 6;
update title set tname = '계약직' where tno = 6;

select deptno, deptname, floor from department;
select deptno, deptname, floor from department where deptno = 1;
insert into department values(5, '마케팅', 3);
delete from department where deptno = 5;
update department set deptname = '디자인' where deptno = 5;

create or replace view vw_full_employee
as
select  e.empno,
		e.empname,
		t.tno as title_no,
		t.tname as title_name,
		e.manager as manager_no,
		m.manager as manager_name,
		e.salary,
		d.deptno,
		d.deptName,
		d.floor
from employee e join title t on e.tno = t.tno 
left join employee m on e.manager = m.empno
join department d on e.deptno = d.deptno;




select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptName, floor
from vw_full_employee;

select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptName, floor
from vw_full_employee
where empno = 1003;

select * from employee;
delete from employee where empno = 1004;
insert into employee values(1004, "천사", 5, 4377, 2000000, 1);
update employee set deptno = 3 where empno = 1004;


select * from title;
select * from department;

insert into title values (6, '인턴');
insert into department values (5, "비상계회부", 19);

delete from title where tno = 6;
delete from department where deptno = 5;