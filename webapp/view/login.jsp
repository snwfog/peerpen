<%@ include file="/view/includes/static/header.jsp" %>

<div class="row well">
    <div class="col-xs-6 col-sm-4 col-md-4"></div>
    <div class="col-xs-6 col-sm-4 col-md-4">
        <h2>Login</h2>

        <form role="form" action="/login.do" method="post">
                <div class="clearfix col-lg-2">
                    <input class="form-control " type="text" id="username" name="username" placeholder="Username">
                </div>
                <div class="clearfix col-lg-2 form-group">
                    <input class="form-control" type="password" id="password" name="password" placeholder="Password">
                </div>
                <div class="clearfix col-lg-2">
                    <button class="btn btn-primary" type="button submit">Sign in</button>
                </div>

        </form>
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>
