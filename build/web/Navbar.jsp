<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- begin of menu -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark shadow-sm">
    <div class="container">
        <!-- Logo + Brand -->
        <a class="navbar-brand d-flex align-items-center" href="home">
            <img src="images/logo.jpg" alt="Logo" width="32" height="32" class="d-inline-block align-top mr-2">
            <strong>Shoes Web</strong>
        </a>

        <!-- Toggle for mobile -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
            aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar content -->
        <div class="collapse navbar-collapse" id="navbarContent">
            <!-- Left navigation -->
            <ul class="navbar-nav mr-auto">
                <c:if test="${sessionScope.account.isAdmin == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="products">Product</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ManagerAccount">Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="history">History</a>
                    </li>
                </c:if>
            </ul>

            <!-- Right side -->
            <ul class="navbar-nav ml-auto">
                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="home">Hello, <strong>${sessionScope.account.user}</strong></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                </c:if>
            </ul>

            <!-- Search & Cart -->
            <form action="search" method="post" class="form-inline ml-3">
                <div class="input-group input-group-sm">
                    <input value="${txtS}" name="txtSearch" type="text" class="form-control" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-outline-light">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="cart">
                    <i class="fa fa-shopping-cart"></i> Cart
                </a>
            </form>
        </div>
    </div>
</nav>
<!-- end of menu -->
