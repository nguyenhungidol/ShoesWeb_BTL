<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payment Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
          crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

    <jsp:include page="Navbar.jsp" />

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Xác nhận đơn hàng thành công!</h1>
            <p class="lead text-muted">Cảm ơn bạn đã mua sắm tại cửa hàng chúng tôi.</p>

            <!-- Nếu muốn hiển thị thông tin thời gian -->
            <p><strong>Thời gian đặt hàng:</strong> ${requestScope.orderDate}</p>

            <!-- Nút thanh toán -->
            <a target="_blank" href="http://sandbox.vnpayment.vn/tryitnow/Home/CreateOrder" 
               class="btn btn-dark rounded-pill py-2 btn-block">Thanh toán online</a>

            <!-- Quay về trang chủ -->
            <a href="home" class="btn btn-outline-primary mt-3">Quay về trang chủ</a>
        </div>
    </section>

    <jsp:include page="Footer.jsp" />

</body>
</html>
