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

<h1>U r logged in!!!Welcome back <%= session.getAttribute("userSession") %>!</h1>

<a href="/logout.do">logout</a>


</body>
</html>