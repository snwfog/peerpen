

<%@ include file="/view/includes/static/header.jsp" %>

<div class="container col-md-3 col-md-offset-3">
    <div class="form-group">
        <h2>Login</h2>
        <form role ="form" action="/login" method="post">
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
<%@ include file="/view/includes/static/footer.jsp" %>
