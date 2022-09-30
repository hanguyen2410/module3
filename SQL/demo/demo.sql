/* 1. In ra danh sách các sản phẩm (MASP,TENSP) do “Trung Quốc” sản xuất.*/
SELECT MaSP,TenSP FROM sanpham WHERE NuocSX = 'Trung Quoc';
/*2. In ra danh sách các sản phẩm (MASP, TENSP) có đơn vị tính là “cây”, ”quyển”.*/
SELECT MaSP,TenSP FROM sanpham WHERE DVT between 'cay' and 'quyen';
/*3. In ra danh sách các sản phẩm (MASP,TENSP) có mã sản phẩm bắt đầu là “B” và kết thúc là “01”.*/
SELECT MaSP,TenSP FROM sanpham WHERE MaSP Like 'B_01';
/*4. In ra danh sách các sản phẩm (MASP,TENSP) do “Trung Quốc” sản xuất có giá từ 20.000 đến 30.000.*/
SELECT MaSP,TenSP FROM sanpham WHERE NuocSX = 'Trung Quoc' AND Gia BETWEEN 20000 AND 30000;
/*5.In ra danh sách các sản phẩm (MASP,TENSP) do “Trung Quốc” hoặc “Thái Lan” sản xuất có giá từ
20.000 đến 30.000.*/
SELECT MaSP,TenSP FROM sanpham WHERE (NuocSX = 'Trung Quoc' OR NuocSX = 'Thai Lan') AND (Gia BETWEEN 20000 AND 30000);
/*6. In ra các số hóa đơn, trị giá hóa đơn bán ra trong ngày 1/1/2007 và ngày 2/1/2007.*/
SELECT * FROM hoadon WHERE NgayMuaHang BETWEEN '2007/1/1' AND '2007/1/2';
/*7. In ra các số hóa đơn, trị giá hóa đơn trong tháng 1/2007, sắp xếp theo ngày (tăng dần) và trị giá của hóa đơn (giảm dần).*/
SELECT * FROM hoadon WHERE NgayMuaHang BETWEEN '2007/1/1' AND '2007/1/31' ORDER BY TriGia DESC;
SELECT * FROM hoadon WHERE NgayMuaHang BETWEEN '2007/1/1' AND '2007/1/31' ORDER BY NgayMuaHang ASC;
/*8. In ra danh sách các khách hàng (MAKH, HOTEN) đã mua hàng trong ngày 1/1/2007.*/
SELECT kh.MAKH,kh.HoTen FROM khachhang kh inner join hoadon hd on hd.MaKH = kh.MaKH WHERE NgayMuaHang = '2007/1/1';
/*9. In ra danh sách các sản phẩm (MASP,TENSP) được khách hàng có tên “Nguyễn Văn A” mua trong tháng 10/2006.*/
SELECT sp.MASP,sp.TENSP FROM sanpham sp inner join cthd ct on sp.MaSP = ct.MaSP
inner join hoadon hd on ct.SoHD = hd.SoHoaDon 
inner join khachhang kh on hd.MaKH = kh.MaKH WHERE (kh.HoTen = 'Nguyen Van A') AND (hd.NgayMuaHang LIKE '2006-10-%%');
/*10. In ra số hóa đơn, trị giá các hóa đơn do nhân viên có tên “Nguyễn Văn B” lập trong ngày 10/10/2006.*/
SELECT hd.SoHoaDon, hd.TriGia FROM hoadon hd inner Join nhanvien nv on nv.MaNV = hd.MaNV WHERE HoTen = 'Nguyen Van B' AND NgayMuaHang = '2006/10/28';
/*11. Tìm các số hóa đơn đã mua sản phẩm có mã số “BB01” hoặc “BB02”.*/
SELECT hd.SoHoaDon FROM hoadon hd Inner Join cthd ct on ct.SoHD = hd.SoHoaDon
Inner Join sanpham sp on ct.MaSP = sp.MaSP WHERE sp.MaSP = 'BB01' OR sp.MaSP = 'BB02';
/*12. Tìm các số hóa đơn đã mua sản phẩm có mã số “BB01” hoặc “BB02”, mỗi sản phẩm mua với số ượng từ 10 đến 20.*/
SELECT hd.SoHoaDon FROM hoadon hd Inner Join cthd ct on ct.SoHD = hd.SoHoaDon
Inner Join sanpham sp on ct.MaSP = sp.MaSP WHERE (sp.MaSP = 'BB01' OR sp.MaSP = 'BB02') AND (ct.SoLuong BETWEEN 10 AND 20);
/*13. Tìm các số hóa đơn mua cùng lúc 2 sản phẩm có mã số “BB01” và “BB02”, mỗi sản phẩm mua với số ượng từ 10 đến 20.*/
SELECT SoHD FROM cthd WHERE (MaSP = 'BB01' AND SoLuong BETWEEN 10 AND 20) AND (SELECT SoHD FROM cthd WHERE MaSP = 'BB02' AND  SoLuong BETWEEN 10 AND 20);
/*14. In ra danh sách các sản phẩm (MASP,TENSP) do “Trung Quốc” sản xuất hoặc các sản phẩm được bán ra trong ngày 1/1/2007.*/
SELECT sp.MaSP,sp.TenSP FROM sanpham sp inner join cthd ct on sp.MaSP = ct.MaSp 
inner join hoadon hd on ct.SoHD = hd.SoHoaDon WHERE sp.NuocSX = 'Trung Quoc' OR hd.NgayMuaHang = '2007/1/1';
/*15. In ra danh sách các sản phẩm (MASP,TENSP) không bán được.*/
SELECT s.MaSP,s.TenSP FROM sanpham s WHERE NOT EXISTS(SELECT sp.MaSP FROM sanpham sp Inner Join cthd ct on sp.MaSP = ct.MaSP AND sp.MaSP = s.MaSP);
/*16. In ra danh sách các sản phẩm (MASP,TENSP) không bán được trong năm 2006.*/
SELECT s.MaSP,s.TenSP 
FROM sanpham s
WHERE s.MaSP NOT IN (SELECT ct.MaSP FROM cthd ct inner join hoadon hd on hd.SoHoaDon = ct.SoHD WHERE hd.NgayMuaHang LIKE '2006-%-%');
/*17. In ra danh sách các sản phẩm (MASP,TENSP) do “Trung Quốc” sản xuất không bán được trong năm 2006.*/
SELECT ss.MaSP,ss.TenSP
FROM sanpham ss
WHERE NuocSX = 'Trung Quoc' AND (ss.MaSP NOT IN (SELECT ct.MaSP FROM cthd ct inner join hoadon hd on hd.SoHoaDon = ct.SoHD WHERE hd.NgayMuaHang LIKE '2006-%-%'));
/*18. Có bao nhiêu hóa đơn không phải của khách hàng đăng ký thành viên mua?*/
SELECT * 
FROM hoadon
WHERE MaKH is null;
/*19. Cho biết trị giá hóa đơn cao nhất, thấp nhất là bao nhiêu ?*/
SELECT Max(TriGia) AS LonNhat,
Min(TriGia) AS NhoNhat
FROM hoadon;
/*20.Trị giá trung bình của tất cả các hóa đơn được bán ra trong năm 2006 là bao nhiêu?*/
SELECT AVG(TriGia) AS TrungBinh
FROM hoadon
WHERE NgayMuaHang LIKE '2006-%-%';
/*21. Tính doanh thu bán hàng trong năm 2006.*/
SELECT SUM(TriGia) AS Tong
FROM hoadon
WHERE NgayMuaHang LIKE '2006-%-%';
/*22. Tìm số hóa đơn có trị giá cao nhất trong năm 2006.*/
SELECT SoHoaDon
FROM hoadon
WHERE TriGia = (SELECT MAX(TriGia) FROM hoadon);
/*23. Tìm họ tên khách hàng đã mua hóa đơn có trị giá cao nhất trong năm 2006.*/
SELECT kh.HoTen FROM khachhang kh inner Join hoadon hd on kh.MaKH = hd.MaKH 
WHERE hd.TriGia = (SELECT MAX(TriGia) FROM hoadon);
/*24.In ra danh sách 3 khách hàng (MAKH, HOTEN) có doanh số cao nhất.*/
SELECT MaKH,HoTen FROM khachhang 
Order BY DoanhSo DESC LIMIT 0,3;
/*25.In ra danh sách các sản phẩm (MASP, TENSP) có giá bán bằng 1 trong 3 mức giá cao nhất.*/
SELECT MaSP,TenSp From sanpham
Order BY Gia DESC LIMIT 0,3;
/*26. In ra danh sách các sản phẩm (MASP, TENSP) do “Trung Quốc” sản xuất có giá bằng 1 trong 3 mức iá thấp nhất (của tất cả các sản phẩm).*/
SELECT MaSP,TenSp From sanpham
WHERE NuocSX = 'Trung Quoc'
Order BY Gia DESC LIMIT 0,3;
/*27. In ra danh sách các sản phẩm (MASP, TENSP) do “Trung Quốc” sản xuất có giá bằng 1 trong 3 mức iá thấp nhất (của sản phẩm do “Trung Quốc” sản xuất).*/
SELECT MaSP,TenSp From sanpham
WHERE NuocSX = 'Trung Quoc'
Order BY Gia ASC LIMIT 0,3;
/*28. * In ra danh sách 3 khách hàng (MAKH, HOTEN) có doanh số cao nhất (sắp xếp theo kiểu xếp hạng).*/
SELECT MaKH,HoTen FROM khachhang 
Order BY DoanhSo DESC LIMIT 0,3;
/*29.Tính tổng số sản phẩm do “Trung Quốc” sản xuất.*/
SELECT COUNT(NuocSX) AS SPTrungQuoc
FROM sanpham
WHERE NuocSX = 'Trung Quoc';
/*30.Tính tổng số sản phẩm của từng nước sản xuất.*/
SELECT COUNT(NuocSX) AS TongSP,
NuocSX
FROM sanpham
GROUP BY NuocSX;
/*31. Với từng nước sản xuất, tìm giá bán cao nhất, thấp nhất, trung bình của các sản phẩm.*/
SELECT NuocSX,
MAX(Gia) as CaoNhat,
MIN(Gia) as NhoNhat,AVG(Gia) as TrungBinh
FROM sanpham
GROUP BY NuocSX;
/*32. Tính doanh thu bán hàng mỗi ngày.*/
SELECT AVG(TriGia) as DoanhThu,
NgayMuaHang
FROM hoadon
GROUP BY NgayMuaHang;
/*33. Tính tổng số lượng của từng sản phẩm bán ra trong ngày 28/10/2006.*/
SELECT sp.TenSP,
MAX(ct.SoLuong) AS SoLuongSanPham
FROM sanpham sp inner Join cthd ct on sp.MaSP = ct.MaSP
inner Join hoadon hd on ct.SoHD = hd.SoHoaDon
where hd.NgayMuaHang = '2006/10/28'
GROUP BY sp.TenSP;
/*34. Tính doanh thu bán hàng của từng tháng trong năm 2006.*/
SELECT NgayMuaHang,
MAX(TriGia) AS DoanhThuBanHang
From hoadon
WHERE NgayMuaHang LIKE '2006-%' AND '2007-%'
GROUP BY NgayMuaHang
ORDER BY NgayMuaHang ASC;
/*35. Tìm khách hàng (MAKH, HOTEN) có số lần mua hàng nhiều nhất.*/
SELECT kh.MaKH,kh.HoTen,
MAX(ct.SoLuong) as NhieuNhat
FROM khachhang kh inner JOIN hoadon hd on kh.MaKH = hd.MaKH
Inner Join cthd ct on hd.SoHoaDon = ct.SoHD 
WHERE ct.SoLuong = (SELECT Max(SoLuong) FROM cthd);
/*36. Tìm sản phẩm (MASP, TENSP) có tổng số lượng bán ra thấp nhất trong năm 2006.*/
SELECT s.MaSp,s.TenSp,
MIN(ct.SoLuong) as ThapNhat
 FROM sanpham s
Inner JOIN cthd ct on s.MaSp = ct.MaSp
WHERE ct.SoLuong = (SELECT Min(SoLuong) FROM cthd);
/*37. Tháng mấy trong năm 2006, doanh số bán hàng thấp nhất ?*/
SELECT *,
MIN(TriGia) AS ThapNhat 
FROM hoadon
WHERE TriGia = (SELECT Min(TriGia) FROM hoadon WHERE NgayMuaHang LIKE '2006-%')
GROUP BY NgayMuaHang;
/*38. Mỗi nước sản xuất, tìm sản phẩm (MASP,TENSP) có giá bán cao nhất.*/
SELECT MaSP,TenSP,
MAX(Gia),
NuocSX
FROM sanpham
GROUP BY NuocSX;
/*39. *Trong 10 khách hàng có doanh số cao nhất, tìm khách hàng có số lần mua hàng nhiều nhất.*/
SELECT *,MAX(ct.SoLuong) as SoLuongNhieuNhat
FROM khachhang kh Inner Join hoadon hd on kh.MaKH = hd.MaKH
Inner Join cthd ct on hd.SoHoaDon = ct.SoHD
WHERE kh.DoanhSo = (SELECT MAX(DoanhSo) FROM khachhang);
/*40. *Tìm nước sản xuất sản xuất ít nhất 3 sản phẩm có giá bán khác nhau*/
SELECT TenSP,MaSP,COUNT(NuocSX)
FROM sanpham
GROUP BY MaSP;