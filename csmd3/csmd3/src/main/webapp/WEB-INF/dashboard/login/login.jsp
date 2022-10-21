<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/app.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
    <link rel="stylesheet" href="/assets/login.css">
    <title>Form login unitop.vn</title>
</head>
<body>

    <div id="wrapper">
        <form action="/login" id="form-login" method="post" name="userLogin">
            <h1 class="form-heading">Form đăng nhập</h1>
            <div class="col-sm-12">
                <c:if test="${!requestScope.errors.isEmpty()&&requestScope.errors!=null }">
                    <c:forEach items="${requestScope.errors}" var="item">
                        <div class="alert alert-warning" role="alert">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                                        class="mdi mdi-close" aria-hidden="true"></span></button>
                                <div class="icon"><span class="mdi mdi-close-circle-o"></span></div>
                                <div class="message">
                                    <span>Error!</span>
                                        ${item}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>


                <c:if test="${requestScope.message!=null}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                                class="mdi mdi-close" aria-hidden="true"></span></button>
                        <div class="icon"><span class="mdi mdi-check"></span></div>
                        <div class="message" style="color: white">
                            <strong><i class="fa-solid fa-check"></i></strong>
                                ${requestScope.message}
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <i class="far fa-user"></i>
                <input type="text" name="username" id="username" class="form-input" placeholder="Tên đăng nhập">
            </div>
            <div class="form-group">
                <i class="fas fa-key"></i>
                <input type="password" name="password" class="form-input" placeholder="Mật khẩu">
                <div id="eye">
                    <i class="far fa-eye"></i>
                </div>
            </div>
            <input type="submit" value="Đăng nhập" class="form-submit">
        </form>
    </div>
    
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="js/app.js"></script>
</html>