<%--
  Created by IntelliJ IDEA.
  User: Piotrek
  Date: 20.01.2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/htmlcharset=ISO-8859-
1">
    <link
            rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/boo
tstrap.min.css">
    <title>Welcome</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1 class="alert alert-danger"> There is no product found
                with the Product id ${invalidProductId}</h1>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <p>${url}</p>
        <p>${exception}</p>
    </div>
    <div class="container">
        <p>
            <a href="<spring:url value="/products" />"class="btn btnprimary">
                <span class="glyphicon-hand-left glyphicon"></span>
                products
            </a>
        </p>
    </div>
</section>
</body>
</html>