<%@ include file="/view/includes/static/header.jsp" %>
<div class="row">
    <div class="col-xs-6 col-md-4"></div>
    <div class="col-xs-6 col-md-4">
        <h2>Login</h2>

        <form role="form" action="/login.do" method="post">
            <fieldset>
                <div class="clearfix">
                    <input type="text" id="username" name="username"
                           placeholder="Username">
                </div>
                <div class="clearfix">
                    <input type="password" id="password" name="password"
                           placeholder="Password">
                </div>
                <button class="btn btn-primary" type="button submit">Sign in</button>
            </fieldset>
        </form>
    </div>
    <div class="col-xs-6 col-md-4"></div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>
