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
                <form method="GET" action="/document.do">
                    <input type="hidden" name="doc_id" value="<%= d.getId().toString()%>"/>

                    <input type="text" name="doc_name" value="<%= d.getDocName() %>" readonly/>
                    <button type="submit" class="btn btn-success">Check Document</button>
                </form>
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