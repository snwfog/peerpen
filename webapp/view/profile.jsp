<!doctype html>
<html lang="en-US">
<head>
    <title>PeerPen - Profile</title>
</head>
<body>


<%
    if (session.getAttribute("userSession") == null){
        response.sendRedirect("/login");
    }


%>

<h1>U r logged in!!! This is your profile page!</h1>
<a href="/logout.do">logout</a>


</body>
</html>