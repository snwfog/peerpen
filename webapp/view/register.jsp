<!doctype html>
<html lang="en-US">
<head>
    <meta charset="utf-8">
    <title>PeerPen - New Peer</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="assets/css/peerpen.css" rel="stylesheet" media="screen">
</head>
<body id="registration">
    <div class="registerContainer">
            <div id="clear"></div>
            <div id="registerHeader">
                <h2 align="center">Registration</h2>
            </div>
            <div id="registerBody">
                <form id="register" action="/register" method="post">
                    <div class="form-group" >
                            <label>First Name</label>
                            <input class="form-control" type="text" name="first_name" />
                            <label> Last name </label>
                            <input class="form-control" type="text" name="last_name" />
                    </div>
                    <div class="form-group">
                        <label> Personal website </label>
                        <input class="span4 form-control" type="text" name="personal_website" />
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

                    <div class="form-group">
                        <label> Username </label>
                        <input class="form-control" type="text" name="user_name"  />
                    </div>

                    <div class="form-group">
                        <label> Password </label>
                        <input class="form-control" type="password" name="password"  />
                    </div>
                    <button type="submit" class="btn btn-primary" value="submit">Register</button>
                </form>
            </div>
            <div id="clear"></div>
    </div>
    <div id="registrationImage">

    </div>


    </div>
</body>
</html>