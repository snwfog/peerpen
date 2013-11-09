<%@ include file="/view/includes/static/header.jsp" %>
<body id="registration">
<div class="registerContainer">
    <div id="clear"></div>
    <div id="registerHeader">
        <h2 align="center">Registration</h2>
    </div>
    <div id="registerBody">
        <form id="register" action="/register.do" method="post">
            <div class="form-group" >
                <label>First Name</label>
                <input class="form-control" type="text" name="first_name" />
                <label> Last name </label>
                <input class="form-control" type="text" name="last_name" />
            </div>
            <div class="form-group">
                <label> Username </label>
                <input class="form-control" type="text" name="user_name"  />
            </div>

            <div class="form-group">
                <label> Password </label>
                <input class="form-control" type="password" name="password"  />
            </div>
            <div class="form-group">
                <label> Confirm Password </label>
                <input class="span4 form-control" type="text" name="confirm_password" />
            </div>
            <%--<tr>--%>
            <%--<td>Sex</td>--%>
            <%--<td><input type="radio" name="sex" value="Male">Male | <input--%>
            <%--type="radio" name="sex" value="Female">Female</td>--%>
            <%--</tr>--%>

            <div class="form-group">
                <label> Email </label>
                <input class="form-control" type="text" name="email"  />
            </div>

            <button type="submit" class="btn btn-primary" value="submit">Register</button>
        </form>
    </div>
    <div id="clear"></div>
</div>
<div id="registrationImage">
</div>
</body>
<%@ include file="/view/includes/static/footer.jsp" %>
