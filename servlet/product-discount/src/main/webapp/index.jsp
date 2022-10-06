<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<h2>Product Discount Calculator</h2>
<form method="post" action="/display-discount">
    <label>Product Description</label>
    <input type="text" name="product" placeholder="Mô tả sản phẩm"/>
    <label>List Price</label>
    <input type="number" name="price" placeholder="Giá niêm yết"/>
    <label>Discount Percent:</label>
    <input type="number" name="discount" placeholder="Tỷ lệ chiết khấu(phần trăm)"/>
    <input type="submit" id="caculate" value="Calculate Discount"/>
</form>
</body>
</html>