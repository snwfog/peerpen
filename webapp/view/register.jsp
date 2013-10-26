<!doctype html>
<html lang="en-US">
<head>
<title>PeerPen - New Peer</title>
</head>
<body>
	<h1 align="center">Become a Peer today! #yolo #awesome #peerpen</h1>
	<form action="/register.do" method="post" align="center">
		<table border="0" align="center">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="first_name" /></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><input type="text" name="last_name" /></td>
			</tr>
			<%--<tr>--%>
				<%--<td>Sex</td>--%>
				<%--<td><input type="radio" name="sex" value="Male">Male | <input--%>
					<%--type="radio" name="sex" value="Female">Female</td>--%>
			<%--</tr>--%>
			<tr>
				<td>Personal website</td>
				<td><input type="text" name="personal_website" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type="text" name="user_name" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>
		<br /> <input type="submit" value="submit" />
	</form>
</body>
</html>