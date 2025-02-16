-- # Display Nth row in SQL
select rownum, ename from employee;

-- # NOTE - rownum only works with < operator, no > or >=

(select * from emp
         where rownum <=4)
MINUS
(
select * from emp
where rownum <=3
)

-- Second version
Select * from (select rownum r, ename, sal from emp)
where r = 4;

Select * from (select rownum r, ename, sal from emp)
where r in (4,5,3);



