<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%--
  Created by IntelliJ IDEA.
  User: waisk
  Date: 10/01/14
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>fdfsfdsfdsfd</title>
</head>
<body>
    <p>dfsddfdsf</p>
</body>

<% Peer peer = (Peer) session.getAttribute("user"); %>
<% Document document =(Document) request.getAttribute("document");%>
<div class="row">
    <div class="col-sm-6 col-md-4">
        <div class="thumbnail">
            <div class="caption">
                <% for (Comment c : (List<Comment>) request.getAttribute("comments"))
                {
                %>
                <div class="panel panel-default">
                    <div class="panel-heading"><%= c.getName() %><div style="float:right"><a href="#"><span class="glyphicon glyphicon-thumbs-up"></span></a> / <a href="#"><span class="glyphicon glyphicon-thumbs-down"></span></a></div></div>
                    <div class="panel-body">
                        <%= c.getMessage() %>
                    </div>
                </div>

                <% } %>
                <div class="panel panel-default">
                    <div class="panel-heading"><%= peer.getFirstName() %> <%= peer.getLastName() %></div>
                    <div class="panel-body">
                        <form method="POST" action="/document.do">

                            <%--<input type="hidden" name="id" value="<%= document.getId()%>"/>--%>
                            <textarea name="comment" rows="4" cols="45"  resize="none">I WANT TO GO UP YOUR DIAGON ALLEY</textarea>
                            <br /><br />
                            <%--<input type="hidden" name="thread" value="<>">--%>
                            <button type="submit" class="btn btn-success">Post</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>