<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<!doctype html>
<html lang="en-US">
<head>
  <meta charset="UTF-8">
  <title>PeerPen - Login</title>
</head>
<body>
<form action="login.do" method="post">
  <h1>Login</h1>

  <h3>Username</h3>
  <label for="username">Username</label>
  <input type="text" id="username" name="username"/><br/>

  <h3>Password</h3>
  <label for="password">Password</label>
  <input type="password" id="password" name="password"/><br/>

  <input type="submit" value="Sign in" />
</form>

</body>
</html>