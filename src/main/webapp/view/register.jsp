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

    <form class="form-inline" name="register" onsubmit="return validateForm()" id="register"
          action="/register" method="post">
        <div style=" max-width:500px; float:left;">
            <h4>Take a first step in building your career!</h4>

            <div class="" id="registrationImage">
            </div>
        </div>
        <div style=" max-width:352px; margin-left:50%">
            <h2>Join Now</h2>
            <h4>Become a member of PeerPen</h4>

            <div class="form-group">
                <input type="text" class="form-control" style="margin:10px 0" id="valid_fname" name="first_name"
                       rel="popover" data-content="<div class='validation'>You need to fill in your first name</div>"
                       data-html="true" placeholder="First Name"/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" style="margin:10px 0" id="valid_lname" name="last_name"
                       placeholder="Last Name"
                       data-content="<div class='validation'>You need to fill in your last name</div>"
                       data-html="true"/>
            </div>
            <input type="text" class="form-control" style="margin:10px 0" id="valid_username" name="user_name"
                   placeholder="Username"
                   data-content="<div class='validation'>You need to fill in with a username</div>" data-html="true"/>
            <input type="password" class="form-control" style="margin:10px 0" id="valid_pass" name="password"
                   placeholder="Password"
                   data-content="<div class='validation'>You need to fill in with a password</div>" data-html="true"/>
            <input type="password" class="form-control" style="margin:10px 0" id="valid_cpass" name="confirm_password"
                   placeholder="Confirmed Password"
                   data-content="<div class='validation'>You need to confirm your password</div>" data-html="true"/>
            <input type="text" class="form-control" style="margin:10px 0" id="valid_email" name="email"
                   placeholder="Email"
                   data-content="<div class='validation'>You need to fill in your valid email address</div>"
                   data-html="true"/>
            <button type="submit" class="btn btn-primary pull-right" value="submit">Sign Up</button>
        </div>
    </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>
