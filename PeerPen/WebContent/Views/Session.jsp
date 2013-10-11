<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Front Page</title>
</head>
<body>
<jsp:useBean id="user" type="com.sunnyd.peerpen.domain.User" scope="session" />
<h1>Welcome to PeerPen <%=user.getUserName()%></h1>

    <p>You have logged in as: <%= user.getUserName()%></p>
    <p>Now everyone knows you logged with this password ----> <%= user.getPassword()%></p>
    
    <p><a href="Views/Logout.jsp">Logout</a></p>
</body>
</html>