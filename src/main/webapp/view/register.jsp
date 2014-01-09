<%@ include file="/view/includes/static/header.jsp" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>
    </div>

</div>
<div class="register">
    <h1>
        Get started!
    </h1>
        <form class="form-inline" id="register" action="/register.do" method="post">
            <div style=" max-width:500px; float:left;">
                <h4>Take a first step in building your career!</h4>
                <div class="" id="registrationImage">
                </div>
            </div>
            <div style=" max-width:352px; margin-left:50%">
                <h2>Join Now</h2>
                <h4>Become a member of PeerPen</h4>
                <div class="form-group">
                    <input type="text" class="form-control" style="margin:10px 0" name="first_name" placeholder="First Name"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" style="margin:10px 0" name="last_name" placeholder="Last Name"/>
                </div>
                <input type="text" class="form-control" style="margin:10px 0" name="user_name" placeholder="Username" />
                <input type="password" class="form-control" style="margin:10px 0" name="password" placeholder="Password" />
                <input type="password" class="form-control" style="margin:10px 0" name="confirm_password" placeholder="Confirmed Password" />
                <input type="text" class="form-control" style="margin:10px 0" name="email" placeholder="Email" />
                <button type="submit" class="btn btn-primary pull-right" value="submit">Sign Up</button>
            </div>
        </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>
