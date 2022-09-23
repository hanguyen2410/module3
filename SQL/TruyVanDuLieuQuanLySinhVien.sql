USE quanlysinhvien;

select *
FROM student
WHERE StudentName LIKE 'h%';

SELECT * 
FROM class  JOIN class C ON C.ClassName = C.ClassName
WHERE  C.StartDate LIKE '%-12-%';

SELECT *
FROM subject 
where Credit between 3 AND 5;

SET SQL_SAFE_UPDATES = 0;

UPDATE student
SET ClassID = 2 
WHERE StudentName = 'Hung';

SELECT *
FROM student,mark,subject
ORDER BY Mark DESC , StudentName ASC;