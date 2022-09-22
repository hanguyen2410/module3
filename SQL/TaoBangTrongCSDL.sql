create database QuanLyDiemThi;

USE QuanLyDiemThi;

CREATE TABLE HocSinh(
MaHS varchar(20) primary key,
TenHS VARCHAR(50),
NgaySinh datetime,
Lop VARCHAR(20),
GT VARCHAR(20)
);

CREATE TABLE MonHoc(
MaMH VARCHAR(20) PRIMARY KEY,
TenMH VARCHAR(50)
);

CREATE TABLE BangDiem(
MaHS VARCHAR(20),
MaMH VARCHAR(20),
DiemThi INT,
NgayKT DATETIME,
primary key(MaHS,MaMH),
foreign key (MaHS) references HocSinh(MaHS),
foreign key(MaMH) references MonHoc(MaMH)
);

create TABLE GiaoVien(
MaGV VARCHAR(20) PRIMARY KEY,
TenGV VARCHAR(20),
SDT VARCHAR(10)
);

ALTER TABLE MonHoc ADD MaGV VARCHAR(20);
alter TABLE MonHoc ADD constraint FK_MaGV foreign key (MaGV) references GiaoVien(MaGV);