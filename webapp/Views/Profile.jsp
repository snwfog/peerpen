<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>PeerPen - Profile</title>
</head>
<body>
	<h1 align="center">This is your profile page!</h1>
	<jsp:useBean id="user" type="com.sunnyd.peerpen.Model.User"
		scope="session" />
	<% String path = (String) session.getAttribute("path")+"/FrontPage"; %>
	<p>
		First Name:
		<%=user.getFirstName()%></p>
	<p>
		Last Name:
		<%=user.getLastName()%></p>
	<p>
		Gender:
		<%=user.getGender()%></p>
	<p>
		Email:
		<%=user.getEmail()%></p>
	<p>
		Password:
		<%=user.getPassword()%></p>
	<p>
		User name:
		<%=user.getUserName()%></p>
	<p>
		Personal Web-site:
		<%=user.getPersonalWebsite()%></p>

	<p>
		<a href="EditProfile">Edit</a>
	</p>

	<p>
	<%=path %>
	<a href=<%=path%>>Home Page</a>
</body>
</html>