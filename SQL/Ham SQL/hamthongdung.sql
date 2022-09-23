USE quanlysinhvien;

SELECT Address, COUNT(StudentId) AS 'Số lượng học viên'
FROM student
GROUP BY Address;

SELECT S.StudentId,S.StudentName,AVG(mark)
FROM Student S join Mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId,S.StudentName;

SELECT S.StudentId,S.StudentName,AVG(mark)
FROM Student S join mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId, S.StudentName
HAVING AVG(mark) > 15;

SELECT S.StudentId,S.StudentName,AVG(mark)
FROM Student S join mark M on S.StudentId = M.StudentId
GROUP BY S.StudentId, S.StudentName
HAVING AVG(mark) >= ALL (SELECT AVG(mark) FROM mark GROUP BY mark.StudentId);