<!doctype html>
<html lang="en-US">
<head>
  <title>PeerPen - Profile</title>
</head>
<body>


<%
  if (session.getAttribute("userSession") == null)
  {
    response.sendRedirect("/login");
  }


%>

<h1><%= session.getAttribute("userSession") %>'s Profile Page</h1>


<a href="/logout.do">logout</a>


</body>
</html>