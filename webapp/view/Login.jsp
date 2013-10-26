<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>This is a fucking Test</title>
    <%--<jsp:include page="../assets/css/bootstrap.css"/>--%>
    <link rel="stylesheet" href="/assets/css/bootstrap.css" type="text/css" >
</head>
<body>
<%@ include file="/view/includes/static/header.jsp" %>
<div class="container">
    <div class="content">
        <div class="row">
            <div class="login-form">
                <h2>Login</h2>
                <form action="login.do" method="post">
                    <fieldset>
                        <div class="clearfix">
                            <input type="text" id="username" name="username" placeholder="Username">
                        </div>
                        <div class="clearfix">
                            <input type="password" id="password" name="password" placeholder="Password">
                        </div>
                        <button class="btn btn-primary" type="button submit">Sign in</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="assets/js/bootstrap.js"></script>
</body>

</html>