<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>

<div class="container">

    <!-- SEARCH FORM -->
    <form action="/search" method="post" class="form-horizontal" role="form">
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-default active">
                <input type="radio" name="area" id="all" value="all" checked /> Search All
            </label>
            <label class="btn btn-default">
                <input type="radio" name="area" id="documents" value="documents" /> Documents
            </label>
            <label class="btn btn-default">
                <input type="radio" name="area" id="peers" value="peers" /> Peers
            </label>
            <label class="btn btn-default">
                <input type="radio" name="area" id="Groups" value="groups" /> Groups
            </label>
        </div>

        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for documents, people and groups" name="search_query" id="search_query" autocomplete="off" />
            <span class="input-group-btn">
                <button type="submit" class="btn btn-primary" name="submit" />Search</button>
            </span>
        </div>
    </form>









    <br /><br /><br /><br /><br />





<!-- SEARCH RESULT -->
<%
    if(session.getAttribute( "searchResults" ) != null){
    ArrayList<Object> results = (ArrayList<Object>) session.getAttribute( "searchResults" );
    if (!results.isEmpty()){
%>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Search Results</div>
        <!-- Table -->
        <table class="table table-hover">
            <%--<tr><th>Type</th><th>Item</th></tr>--%>
<%
    for(int i =0;i< results.size();i++){
        Object resultItem = results.get( i );
        String itemClass = resultItem.getClass().getCanonicalName();
        // each result item will have different look based on its obj type
        if(itemClass.endsWith( "Document" )){
            Document document = (Document) resultItem;
            %>
            <tr><td>Document</td><td><a href="/peer/<%= document.getPeerId() %>/document/<%= document.getId() %>"> <%= document.getDocName() %></a>
            by: <a href="/peer/<%= document.getPeerId() %>"> <%= document.getPeer().getUserName() %></a>
            last modified: <%= document.getLastModifiedDate() %></td></tr>

            <%
            }else if(itemClass.endsWith( "Peer" )){
                Peer peer = (Peer) resultItem;
            %>

            <tr><td>Peer</td><td><a href="/peer/<%= peer.getId() %>/profile"> <%= peer.getUserName() %></a>
            (<%= peer.getFirstName() %> <%= peer.getLastName() %>)
            point: <%= peer.getPoint() %></td></tr>

            <%
            }else if(itemClass.endsWith( "Group" )){
                Group group = (Group) resultItem;
            %>

            <tr><td>Group</td><td><a href="/group/<%= group.getId() %>"><%= group.getGroupName() %></a>
            - <i><%= group.getDescription() %></i></td></tr>

            <%
            }
    }
%>
        </table>
    </div>

<%
        }}
%>




    <br /><br /><br /><br /><br /><br /><br /><br />
</div>

<%@ include file="/view/includes/static/footer.jsp" %>