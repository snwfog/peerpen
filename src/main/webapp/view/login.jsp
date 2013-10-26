<%@ include file="/view/includes/static/header.jsp" %>


<div class="container">
    <form style="max-width:330px; margin:0 auto" action="/login.do" method="post">
        <h2>Please sign in</h2>
        <input type="text" class="form-control" style="margin:10px 0" placeholder="Email address" autofocus="">
        <input type="password" class="form-control" style="margin:10px 0" placeholder="Password">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div>

<%--<div class="row">--%>
    <%--<div class="col-xs-6 col-md-4"></div>--%>
    <%--<div class="col-xs-6 col-md-4">--%>
        <%--<h2>Login</h2>--%>

        <%--<form role="form" action="/login.do" method="post">--%>
            <%--<fieldset>--%>
                <%--<div class="clearfix">--%>
                    <%--<input type="text" id="username" name="username"--%>
                           <%--placeholder="Username">--%>
                <%--</div>--%>
                <%--<div class="clearfix">--%>
                    <%--<input type="password" id="password" name="password"--%>
                           <%--placeholder="Password">--%>
                <%--</div>--%>
                <%--<button class="btn btn-primary" type="button submit">Sign in</button>--%>
            <%--</fieldset>--%>
        <%--</form>--%>
    <%--</div>--%>
    <%--<div class="col-xs-6 col-md-4"></div>--%>
<%--</div>--%>


<%@ include file="/view/includes/static/footer.jsp" %>
