<%@page import="com.sunnyd.peerpen.manager.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>PeerPen - Login</title>
</head>
<body>
	<form action="Login" method="post">
	<h1>Login</h1>
   
    <h3>Username</h3>
	<input type="text" name="un"/><br/>
	<h3>Password</h3>
	<input type="password" name="pw"/><br/>
	<input type="submit" value="submit"/><br/>
	</form>
   
 <%--    <jsp:useBean id="user" type="com.sunnyd.peerpen.domain.User" scope="request" />
    <p>Artist Name: <%= user.getUserName()%></p>
    <p>Artist Info: <%= user.getPassword()%></p>
 --%>
</body>
</html>