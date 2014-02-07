<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Document" %>

<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) request.getAttribute( "peer" ); %>
<% Peer sessionUser = (Peer) request.getAttribute( "sessionUser" ); %>
<% List<Document> documents = peer.getDocuments(); %>

<div class="container-fluid">

    <h1>You are viewing <%= (sessionUser.getId() == peer.getId())  ? "your own" : peer.getFirstName()+"&#39;s" %> documents</h1>

    <h2>Number of documents: <%= peer.getDocuments().size()%>
    </h2>

    <div id="contentFlow" class="ContentFlow">
        <!-- should be place before flow so that contained images will be loaded first -->
        <div class="loadIndicator"><div class="indicator"></div></div>

        <div class="flow">

            <% for(Document d : documents) {%>
                <div class="item">

                        <img class="content" src="/assets/images/clipart-document-management.png"/>

                    <div class="caption">
                        <a href="/peer/<%= peer.getId() %>/document/<%= d.getId()%>">
                            <%=d.getDocName()%>
                        </a>
                    </div>
                </div>
            <%}%>



        </div>
        <div class="globalCaption"></div>
        <div class="scrollbar">
            <div class="slider"><div class="position"></div></div>
        </div>

    </div>
</div>

<script src="/assets/js/lib/contentflow/contentflow_src.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>