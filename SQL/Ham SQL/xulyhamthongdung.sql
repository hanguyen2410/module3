USE quanlysinhvien;

SELECT SubName as 'Tên',
MAX(Credit) as 'Credit'
FROM subject
HAVING MAX(Credit);

SELECT s.StudentName as "Têm",max(M.Mark) as "ĐTB" , sub.SubName
FROM subject sub, student s, mark M
WHERE sub.SubId = (SELECT M.SubId From mark WHERE Mark = (SELECT MAX(Mark) From Mark));


SELECT * , AVG(M.Mark)
FROM subject sub, student s, mark M
WHERE s.StudentId = (select M.SubId from mark Where mark = (select AVG(Mark) from mark));
