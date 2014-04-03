<%@ include file="/view/includes/static/header.jsp" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/navbar.jsp" %>


<%--Declare all request variables here, easy to debug!!!--%>
<% Peer peer = (Peer) request.getAttribute( "peer" ); %>
<% List<Document> documents = peer.getDocuments(); %>

<div class="container-fluid">
    <%--<div class="row">--%>
        <%--<div class="col-md-4">--%>
        <%--</div>--%>

        <%--<div class="col-md-4">--%>
            <%--<div class="row text-center" id="documentBar">--%>
                <%--<input type="hidden" value="<%=peer.getId()%>" id="ajaxPeer">--%>
                <%--<a class="btn btn-warning btn-sm" href="#" id="viewDocument" disabled>View Detail Document</a>--%>
                <%--<a class="btn btn-warning btn-sm" href="#" id="editDocument" disabled>Edit Document</a>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="col-md-4">--%>
        <%--</div>--%>
    <%--</div>--%>

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
            <div class="navbar-inverse documents-slider-header">
                <div class="row text-center">
                    <a href="documentsFlow" class="" style="font-size:18px;
    color:#fff;" id="slideDiv">Slide Document Navigator</a>
                </div>
            </div>
        </div>
        <div class="row documents-slider-content">
            <div id="documentsFlow" class="documents-slider">
                <div id="contentFlow" class="ContentFlow ContentFlowAddOn_black">
                    <div class="documents-clear-box">
                    </div>
                    <!-- should be place before flow so that contained images will be loaded first -->
                    <div class="loadIndicator documents-indicator" style="background-color:transparent;"><div class="indicator"></div></div>

                    <div class="flow">
                        <% for(Document d : documents) {%>
                        <div class="item">
                            <img class="content" src="/assets/images/clipart-document-management.png" id="document-<%=d.getId()%>"/>
                            <div class="caption documents-body-item">
                                <a href="/peer/<%= peer.getId() %>/document/<%= d.getId()%>" class="documents-body-item-text" style="font-style:normal; color:#000; font-weight:400;">
                                    <%=d.getDocName()%>
                                </a>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <div class="globalCaption"></div>
                    <div class="scrollbar">
                        <div class="slider" style="width:48px; height:16px; cursor: pointer;"><div class="position" style="color:transparent;"></div></div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>

<script src="/assets/js/lib/contentflow/contentflow_src.js"></script>
<%@ include file="/view/includes/static/footer.jsp" %>

