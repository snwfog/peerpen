<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>PeerPen - Edit Profile</title>
</head>
<body>
	<h1 align="center">Edit Page!</h1>
	<jsp:useBean id="user" type="com.sunnyd.peerpen.Model.User"
		scope="session" />
	<form method="post" align="center">
		<p>
			First Name: <input type="text" name="first_name"
				value="<%=user.getFirstName()%>">
		</p>
		<p>
			Last Name: <input type="text" name="last_name"
				value="<%=user.getLastName()%>">
		</p>
		<p>
			Gender:
			<%
			if (user.getGender() == user.getGender()) {
				out.println("<input type=\"radio\" name=\"sex\" value=\"Male\" checked=\"checked\">Male | <input type=\"radio\" name=\"sex\" value=\"Female\">Female");
			} else {
				out.println("<input type=\"radio\" name=\"sex\" value=\"Male\" >Male | <input type=\"radio\" name=\"sex\" value=\"Female\" checked=\"checked\">Female");
			}
		%>
		</p>
		<p>
			Email: <input type="text" name="email" value="<%=user.getEmail()%>">
		</p>
		<p>
			Password: <input type="text" name="password"
				value="<%=user.getPassword()%>">
		</p>
		<p>
			User name: <input type="text" name="username"
				value="<%=user.getUserName()%>">
		</p>
		<p>
			Personal Web-site: <input type="text" name="personal_website"
				value="<%=user.getPersonalWebsite()%>">
		</p>
		<input type="hidden" name="method" value="edit"> <br /> <input
			type="submit" value="submit" />
</body>
</html>