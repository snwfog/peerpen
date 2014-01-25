<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Document" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) request.getAttribute( "peer" ); %>
<% List<Document> documents = peer.getDocuments(); %>

<div class="container-fluid">
    <h1>You are viewing <%= peer.getFirstName()%>&#39; documents</h1>

    <h2>Number of documents: <%= peer.getDocuments().size()%>
    </h2>


    <% for ( int i = 1; i < documents.size() + 1; i++ ) { %>
    <% Document document = documents.get( i - 1 ); %>
    <% if ( (i % 3) == 1 ) { %>
    <div class="row-fluid">
        <% } %>
        <div class="span4">
            <div class="card">
                <h2 class="card-heading simple"><%= document.getDocName() %>
                </h2>

                <div class="card-body">
                    <%--<p>isus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
                    <p><a class="btn" href="/peer/<%= peer.getId() %>/document/<%= document.getId()%>">View
                        document &raquo;</a></p>
                </div>
            </div>
        </div>
        <% if ( (i % 3) == 0 ) { %>
    </div>
    <% } %>
    <% } %>


    <%--<div class="row-fluid">--%>
    <%--<div class="span4">--%>
    <%--<div class="card">--%>
    <%--<h2 class="card-heading simple">Heading</h2>--%>
    <%--<div class="card-body">--%>
    <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
    <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="span4">--%>
    <%--<div class="card">--%>
    <%--<h2 class="card-heading simple">Heading</h2>--%>
    <%--<div class="card-body">--%>
    <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
    <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="span4">--%>
    <%--<div class="card">--%>
    <%--<h2 class="card-heading simple">Heading</h2>--%>
    <%--<div class="card-body">--%>
    <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
    <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>


    <%--<div class="row-fluid">--%>
    <%--<div class="span4">--%>
    <%--<div class="card">--%>
    <%--<h2 class="card-heading simple">Heading</h2>--%>
    <%--<div class="card-body">--%>
    <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
    <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div><!--/span-->--%>
    <%--<div class="span4">--%>
    <%--<div class="card">--%>
    <%--<h2 class="card-heading simple">Heading</h2>--%>
    <%--<div class="card-body">--%>
    <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
    <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div><!--/span-->--%>
    <%--<div class="span4">--%>
    <%--<div class="card">--%>
    <%--<h2 class="card-heading simple">Heading</h2>--%>
    <%--<div class="card-body">--%>
    <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
    <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div><!--/span-->--%>
    <%--</div><!--/row-->--%>
</div>
<!--/row-->
<%@ include file="/view/includes/static/footer.jsp" %>