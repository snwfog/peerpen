<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<div class="register">
    <h1>
        Get started!
    </h1>

    <form class="form-inline" name="register" id="register"
          action="/register" method="post" parsley-validate>
        <div style=" max-width:500px; float:left;">
            <h4>Take a first step in building your career!</h4>

            <div class="" id="registrationImage">
            </div>
        </div>
        <div style=" max-width:352px; margin-left:50%">
            <h2>Join Now</h2>
            <h4>Become a member of PeerPen</h4>

            <div class="form-group">
                <input type="text" class="form-control parsley-validated" style="margin:10px 0" id="valid_fname" name="first_name"
                       rel="popover" data-content="<div class='validation'>You need to fill in your first name</div>"
                       data-html="true" placeholder="First Name" parsley-trigger="change keyup"/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control parsley-validated" style="margin:10px 0" id="valid_lname" name="last_name"
                       placeholder="Last Name"
                       data-content="<div class='validation'>You need to fill in your last name</div>"
                       data-html="true" parsley-trigger="change keyup"/>
            </div>
            <input type="text" class="form-control parsley-validated" style="margin:10px 0" id="valid_username" name="user_name"
                   placeholder="Username" parsley-trigger="change keyup"/>
            <input type="password" class="form-control parsley-validated" style="margin:10px 0" id="valid_pass" name="password"
                   placeholder="Password" parsley-trigger="change keyup"/>
            <input type="password" class="form-control parsley-validated" style="margin:10px 0" id="valid_cpass" name="confirm_password"
                   placeholder="Confirmed Password" parsley-trigger="change keyup"/>
            <input type="text" class="form-control parsley-validated" style="margin:10px 0" id="valid_email" name="email"
                   placeholder="Email"  parsley-trigger="change keyup"/>
            <button type="submit" class="btn btn-primary pull-right" value="submit">Sign Up</button>
            <div id="errorContainer">
            </div>
        </div>
    </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>
