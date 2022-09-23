USE quanlybanhang;

INSERT INTO customer
VALUES (1,'Minh Quan',10);
INSERT INTO Customer
VALUES (2,'Ngoc Oanh',20);
INSERT INTO Customer
VALUES (3,'Hong Ha',50);

INSERT INTO orders
VALUES(1,1,'2006-3-21',0);
INSERT INTO orders
VALUES(2,2,'2006-3-23',0);
INSERT INTO orders
VALUES(3,1,'2006-3-16',0);

INSERT INTO product
VALUES(1,'May Giat',3);
INSERT INTO product
VALUES(2,'Tu Lanh',5);
INSERT INTO product
VALUES(3,'Dieu Hoa',7);
INSERT INTO product
VALUES(4,'Quat',1);
INSERT INTO product
VALUES(5,'Bep Dien',2);

INSERT INTO orderdetail
VALUES (1,1,'3'),(1,3,'7'),(1,4,'2'),(2,1,'1'),(3,1,'8'),(2,5,'4'),(2,3,'3');

SELECT *
FROM orders
ORDER BY oID,oDate,oTotalPrice;

SELECT *
FROM customer C
INNER JOIN orders O On C.cID = O.cID
INNER JOIN orderdetail OD On O.oID = OD.oID
INNER JOIN product P On OD.pID = P.pID;

SELECT *
FROM customer C
WHERE  C.cID NOT IN (SELECT C.cID FROM(customer INNER JOIN orders O On C.cID = O.cID));

SELECT O.oID as "ORder ID",
O.oDate as "Ngày",
sum(OD.odQTY * P.pPrice) as "ToTal"
From ((orderdetail OD inner JOIN product P on OD.pID = P.pID) Inner JOIN orders O On OD.oID = O.oID)
GROUP BY O.oID;