# employee name starts with 'M'
select ename from emp
where ename like 'M%';

# ends with 'M'
select ename from emp
where ename like '%M';

# 'M' in any position of their name
select ename from emp
where ename like '%M%';

# 'M' is not in name
select ename from emp
where ename not like '%M%';

# name of 4 characters
select ename from emp
where ename like '____';

# 2nd letter as 'L' 4th letter as 'M'
select ename from emp
where ename like '_L_M%';

# Display the names and hiredates who joined in December
select hiredate,ename from employee
where hiredate like '%DEC%'

# Employee names containing exactly 2 Ls
select ename from emp
where ename like '%L%L%';


