<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if IE 7]><html class="ie ie7"><![endif]-->
<!--[if IE 8]><html class="ie ie8"><![endif]-->
<!--[if IE 9]><html class="ie ie9"><![endif]-->
<html lang="vi">

<head>
    <jsp:include page="/layout/meta.jsp">
        <jsp:param name="page" value="user"/>
    </jsp:include>
</head>

<body>
    <div class="ps-search">
        <div class="ps-search__content"><a class="ps-search__close" href="#"><span></span></a>
            <form class="ps-form--search-2" action="do_action" method="post">
                <h3>Enter your keyword</h3>
                <div class="form-group"><input class="form-control" type="text" placeholder=""><button
                        class="ps-btn active ps-btn--fullwidth">Search</button></div>
            </form>
        </div>
    </div>
    <jsp:include page="/layout/header.jsp"></jsp:include>
    <div class="ps-hero bg--cover" data-background="/assets/images/hero/blog.jpg">
        <div class="ps-hero__content">
            <h1>NHÀ HÀNG VỮNG HUẾ</h1>
            <div class="ps-breadcrumb">
                <ol class="breadcrumb">
                    <li><a href="/user?action=create">Add User</a></li>
                </ol>
            </div>
        </div>
    </div>
    <main class="ps-main">
        <div class="ps-container">
            <div class="row">
                <c:forEach items="${requestScope.listUser}" var="user">
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 ">
                    <div class="ps-post text-center " style="font-size: 30px" data-mh="post">

                            <div class="ps-post__thumbnail"><img style="width: 250px;height: 500px" src="${user.getImages()}" alt=""><a
                                    class="ps-post__overlay" href="blog-detail.html"></a></div>
                            <div class="ps-post__content"><span style="font-size: 30px" class="ps-post__posted">FullName :  ${user.getFullname()}</span>
                                <p class="ps-post__thumbnail" href="blog-detail.html" style="font-size: 30px">UserName : ${user.getUsername()}</p>
                                <p class="ps-post__byline" style="font-size: 30px">Phone : ${user.getPhone()}</p>
                                <p style="font-size: 30px">Email : ${user.getEmail()}</p>
                                <p style="font-size: 30px"> Country :${user.getCountry()}</p>
                                <a class="btn-group-justified btn-primary" href="/user?action=edit&id=${user.getId()}">Update</a>
                                <a class="btn-group-justified btn-danger" onclick="showMessage(${user.getId()})">Delete</a>
                            </div>

                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="ps-pagination">
                <ul class="pagination">
                    <c:if test="${requestScope.currentPage != 1}">
                    <li class="page-link"><a class="page-link" href="user?page=${requestScope.currentPage - 1}"><i class="fa fa-angle-double-left"></i></a></li>
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <li class="page-item"><a class="page-link" href="user?page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item ">
                            <a class="page-link"
                               href="user?page=${i}">${i}</a></li>
                    </c:otherwise>
                    </c:choose>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <li><a class="page-link" href="user?page=${requestScope.currentPage + 1}"><i class="fa fa-angle-double-right"></i></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </main>
    <jsp:include page="/layout/footer.jsp"></jsp:include>
    <div id="back2top"><i class="fa fa-angle-up"></i></div>
    <jsp:include page="/layout/footerjs.jsp"></jsp:include>
    <script>
        function showMessage(id) {
            if (confirm("Bạn có muốn xóa sản phẩm này không?") === true) {
                window.location.href = "/user?action=delete&id=" + id;
            }
        }
    </script>
</body>

</html>