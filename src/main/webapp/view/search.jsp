<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>
<script src="/assets/js/custom/search_autocomplete_caller.js"></script>

<div class="container">
    <h1>Search</h1>

    <form action="/search.do" method="get" class="form-horizontal" role="form">
        <div class="form-group">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Start typing ..." name="search_query" id="search_query" autocomplete="off" />
                <span class="input-group-btn">
                    <button type="submit" class="btn btn-primary" name="submit" />Search</button>
                </span>
            </div>

            <div class="btn-group" data-toggle="buttons">
                <label class="btn btn-default">
                    <input type="radio" name="area" id="all" value="all" checked /> All
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

        </div>
        <%--<ul id="suggestion_list" style="background-color:white;width:200px;margin:auto"></ul>--%>
    </form>


    <!-- Handling search result -->
    <%
        if(session.getAttribute( "searchResults" ) != null){
            ArrayList<Object> results = (ArrayList<Object>) session.getAttribute( "searchResults" );
            for(int i =0;i< results.size();i++){
                Object resultItem = results.get( i );
                String itemClass = resultItem.getClass().getCanonicalName();
                // each result item will have different look based on its obj type
                if(itemClass.endsWith( "Document" )){
                    Document document = (Document) resultItem;
                    %>
                    [Document] <a href="/document/<%= document.getId() %>"> <%= document.getDocName() %></a>
                    by: <a href="/peer/<%= document.getPeerId() %>"> <%= document.getPeer().getUserName() %></a>
                    last modified: <%= document.getLastModifiedDate() %><br />
                    <%
                }else if(itemClass.endsWith( "Peer" )){
                    Peer peer = (Peer) resultItem;
                    %>
                    [Peer] <a href="/peer/<%= peer.getId() %>"> <%= peer.getUserName() %></a>
                    (<%= peer.getFirstName() %> <%= peer.getLastName() %>)
                    point: <%= peer.getPoint() %><br />
                    <%
                }else if(itemClass.endsWith( "Group" )){
                    Group group = (Group) resultItem;
                    %>
                    [Group] <a href="/group/<%= group.getId() %>"><%= group.getGroupName() %></a>
                    - <i><%= group.getDescription() %></i><br />
                    <%
                }
            }
        }
    %>

    <br /><br /><br /><br /><br /><br /><br /><br />
</div>

<%@ include file="/view/includes/static/footer.jsp" %>