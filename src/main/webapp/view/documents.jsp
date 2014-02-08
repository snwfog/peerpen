<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>
<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) request.getAttribute( "peer" ); %>
<% List<Document> documents = sessionUser.getDocuments(); %>

<div class="container-fluid">
    <div class="row">
        <h1>You are viewing <%= (sessionUser.getId() == peer.getId())  ? "your own" : peer.getFirstName()+"&#39;s" %> documents</h1>

        <h2>Number of documents: <%= peer.getDocuments().size()%>
        </h2>

        <div id="contentFlow" class="ContentFlow">
            <!-- should be place before flow so that contained images will be loaded first -->
            <div class="loadIndicator"><div class="indicator"></div></div>

            <div class="flow">

                <% for(Document d : documents) {%>
                    <div class="item">

                            <img class="content" src="/assets/images/clipart-document-management.png" id="document-<%=d.getId()%>"/>

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

    <div class="row">
        <div id="currentDocument">

        </div>
    </div>
</div>

<script src="/assets/js/lib/contentflow/contentflow_src.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>