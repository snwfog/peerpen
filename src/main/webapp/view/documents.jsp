<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/header.jsp" %>

<% Peer peer = (Peer) session.getAttribute("user"); %>
<div class="row">
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">

      <% for (Document d : (List<Document>) request.getAttribute("documents"))
      {
      %>
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="/documents.do"><%= d.getDocName() %></a>
            </div>
        </div>
        <% } %>
      <%--<div class="caption">--%>
        <%--<% for (Document c : (List<Document>) request.getAttribute("documents"))--%>
        <%--{--%>
        <%--%>--%>
        <%--<div class="panel panel-default">--%>
          <%--<div class="panel-heading"><%= c.getDocName() %>--%>
        <%--</div>--%>

        <%--<% } %>--%>

      <%--</div>--%>
    </div>
  </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>