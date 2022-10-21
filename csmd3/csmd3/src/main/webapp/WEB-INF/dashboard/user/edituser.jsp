<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 7]><html class="ie ie7"><![endif]-->
<!--[if IE 8]><html class="ie ie8"><![endif]-->
<!--[if IE 9]><html class="ie ie9"><![endif]-->
<html lang="en">

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
    </div><!-- header-->
    <jsp:include page="/layout/header.jsp"></jsp:include>
    <div class="ps-hero bg--cover" data-background="/assets/images/hero/about.jpg">
        <div class="ps-hero__content">
            <h1>Edit User</h1>
            <div class="ps-breadcrumb">
                <ol class="breadcrumb">
                    <li><a href="/user">Home</a></li>
                    <li class="active">Edit User</li>
                </ol>
            </div>
        </div>
    </div>
    <div class="ps-checkout pt-40 pb-40">
        <div class="ps-container">
            <div class="row">
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
                            <div class="message">
                                <strong><i class="fa-solid fa-check"></i></strong>
                                    ${requestScope.message}
                            </div>
                        </div>
                    </c:if>
                </div>
            <form class="ps-form--checkout" action="/user?action=edit&id=${user.getId()}" method="post">
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 ">
                        <div class="ps-checkout__billing">
                            <h3>Dish Details</h3>
                            <div class="form-group form-group--inline"><label>FullName<span>*</span></label>
                                <div class="form-group__content"><input value="${requestScope.user.getFullname()}" name="fullname" class="form-control" type="text"></div>
                            </div>
                            <div class="form-group form-group--inline"><label>UserName<span>*</span></label>
                                <div class="form-group__content"><input class="form-control" name="username" value="${requestScope.user.getUsername()}" type="text"></div>
                            </div>
                            <div class="form-group form-group--inline"><label>PassWord<span>*</span></label>
                                <div class="form-group__content"><input class="form-control" name="password" value="${requestScope.user.getPassword()}" type="text"></div>
                            </div>
                            <div class="form-group form-group--inline"><label>Phone<span>*</span></label>
                                <div class="form-group__content"><input class="form-control" name="phone" value="${requestScope.user.getPhone()}" type="text"></div>
                            </div>
                            <div class="form-group form-group--inline"><label>Email<span>*</span></label>
                                <div class="form-group__content"><input class="form-control" name="email" value="${requestScope.user.getEmail()}" type="text"></div>
                            </div>
                            <div class="form-group form-group--inline"><label>Country<span>*</span></label>
                                <div class="form-group__content"><input class="form-control" name="country" value="${requestScope.user.getCountry()}" type="text"></div>
                            </div>
                            <div class="form-group form-group--inline"><label>Images<span>*</span></label>
                                <div class="form-group__content"><input class="form-control" name="images" value="${requestScope.user.getImages()}" type="text"></div>
                            </div>
                        </div>
                    </div>
                        </div>
                    <div class="form-group paypal">
                        <button class="ps-btn ps-btn--yellow" style="margin-left: 300px;">Edit User</button>
                    </div>
            </form>
        </div>
                </div>
        </div>
    </div>
    <div class="ps-site-features">
        <div class="ps-container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 ">
                    <div class="ps-block--iconbox" data-mh="icon"><i class="ba-delivery-truck-2"></i>
                        <h4>Free Shipping <span>On Order Over $199</span></h4>
                        <p>Want to track a package? Find tracking information and order details from Your Orders.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 ">
                    <div class="ps-block--iconbox" data-mh="icon"><i class="ba-biscuit-1"></i>
                        <h4>Master Chef<span>WITH PASSION</span></h4>
                        <p>Shop zillions of finds, with new arrivals added daily.</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 ">
                    <div class="ps-block--iconbox" data-mh="icon"><i class="ba-flour"></i>
                        <h4>Natural Materials<span>protect your family</span></h4>
                        <p>We always ensure the safety of all products of store</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 ">
                    <div class="ps-block--iconbox" data-mh="icon"><i class="ba-cake-3"></i>
                        <h4>Attractive Flavor <span>ALWAYS LISTEN</span></h4>
                        <p>We offer a 24/7 customer hotline so you’re never alone if you have a question.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/layout/footer.jsp"></jsp:include>
    <div id="back2top"><i class="fa fa-angle-up"></i></div>
    <div class="ps-loading">
        <div class="rectangle-bounce">
            <div class="rect1"></div>
            <div class="rect2"></div>
            <div class="rect3"></div>
            <div class="rect4"></div>
            <div class="rect5"></div>
        </div>
    </div><!-- Plugins-->
    <jsp:include page="/layout/footerjs.jsp"></jsp:include>
</body>

</html>