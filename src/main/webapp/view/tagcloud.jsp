<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>


<br /><br /><br />
<div class="container">
    <!-- SEARCH FORM -->
    <form action="/tagcloud" method="post" class="form-horizontal" role="form">
        <div class="input-group">
            <%--<input type="text" class="form-control" name="tag_query" id="tag_query" autocomplete="off" />--%>
            <ul id="tag_query" class="form-control" style="overflow-y:hidden"></ul>
            <span class="input-group-btn">
                <button type="submit" class="btn btn-primary" name="submit" />Search</button>
            </span>
        </div>
    </form>


    <br /><Br /><br />



    <!-- TAG CLOUD -->
    <ul class="tagit ui-widget ui-widget-content ui-corner-all" style="border:0">
    <%
        Map<String, Object> map = new HashMap<String, Object>();
        List<TagDescriptor> tagDescriptors = new TagDescriptor().findAll( map );
        for(int i=0;i<tagDescriptors.size();i++){
            TagDescriptor td = tagDescriptors.get( i );
            %>
        <li class="tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable">
            <span class="tagit-label">
                <%= td.getTagName() %>
            </span>
        </li>
    <%
        }
    %>
    </ul>

    <hr />



<!-- SEARCH RESULT -->
<%
    if(request.getAttribute( "tagSearchResultsGroups" ) != null && request.getAttribute( "tagSearchResultsDocuments" ) != null){
        ArrayList<Group> gs = (ArrayList<Group>) request.getAttribute( "tagSearchResultsGroups" );
        ArrayList<Document> documents = (ArrayList<Document>) request.getAttribute( "tagSearchResultsDocuments" );
    if (!gs.isEmpty() || !documents.isEmpty()){
%>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Search Results</div>
        <!-- Table -->
        <table class="table table-hover">
            <%--<tr><th>Type</th><th>Item</th></tr>--%>
<%
    for(int i =0;i< gs.size();i++){
        Group group = gs.get( i );
        %>
        <tr><td>Group</td><td><a href="/group/<%= group.getId() %>"><%= group.getGroupName() %></a> - <i><%= group.getDescription() %></i></td></tr>
        <%
    }

    for(int i =0;i< documents.size();i++){
        Document document = documents.get( i );
    %>
        <tr><td>Document</td><td><a href="/document/<%= document.getId() %>"> <%= document.getDocName() %></a>
        by: <a href="/peer/<%= document.getPeerId() %>"> <%= document.getPeer().getUserName() %></a>
        last modified: <%= document.getLastModifiedDate() %></td></tr>
    <%
    }
%>

        </table>
    </div>
<%
    }}
%>






    <br /><br /><br /><br /><br />

    <!-- SAMPLE CODE -->
<%--<% Group g = new Group(  ).find( 3 ); %>--%>
<%--Tags attached to: <%=g.getGroupName()%> group:--%>
<%--<div>--%>
    <%--<% List<TagDescriptor> tds = g.getTagDescriptors(); %>--%>
    <%--<form action="/tag.do" method="post" class="form-horizontal" role="form">--%>
        <%--<input type="hidden" name="entityType" value="group" />--%>
        <%--<input type="hidden" name="entityId" value="3" />--%>
        <%--<ul id="entityTags">--%>
        <%--<% for (TagDescriptor td : tds){ %>--%>
        <%--<li><%=td.getTagName() %></li>--%>
        <%--<% } %>--%>
        <%--</ul>--%>
        <%--<button type="submit" class="btn btn-primary" name="submit" />Save Tags</button>--%>
    <%--</form>--%>
<%--</div>--%>



 </div>
<%@ include file="/view/includes/static/footer.jsp" %>