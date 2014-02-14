<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>


<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) request.getAttribute( "peer" ); %>
<% List<Document> documents = peer.getDocuments(); %>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4">
            <div class="row text-center" id="documentBar">
                <input type="hidden" value="<%=peer.getId()%>" id="ajaxPeer">
                <a class="btn btn-primary" href="#" id="viewDocument" disabled>View Detail Document</a>
                <a class="btn btn-primary" href="#" id="editDocument" disabled>Edit Document</a>
            </div>
        </div>

        <div class="col-md-4">
        </div>
    </div>


    <div class="row top-buffer">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <div id="currentDocument">
                <div class="row text-center">
                    <h2> SELECT A DOCUMENT </h2>
                    <img src="/assets/images/clipart-document-management.png"/>
                    <div class="top-buffer">
                        <p>&nbsp</p>
                    </div>
                    <div class="top-buffer">
                        <p>&nbsp</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2">
        </div>
    </div>
</div>

<div class="navbar-fixed-bottom">
    <div class="container-fluid">
        <div class="row">
            <div class="navbar-inverse">
                <div class="row text-center">
                    <a href="documentsFlow" id="slideDiv" class="">Slide Document Navigator</a>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="documentsFlow">
                <div id="contentFlow" class="ContentFlow" style="background-color:white;">
                    <div class="top-buffer">
                        <p>&nbsp</p>
                    </div>
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
        </div>

    </div>
</div>

<script src="/assets/js/lib/contentflow/contentflow_src.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>

