<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>

<script>
$(function() {
   $( "#search_query" ).autocomplete({
       source: function(request, response) {
           $.ajax({
               url: "/search_autocomplete_ajax.do",
               dataType: "json",
               data: {
                   term: request.term,
                   area: $("input[type='radio'][name='area']:checked").val()
               },
               success: function(data) {
                   response(data);
               }
           });
       },
        //
        //source: "search_autocomplete_ajax.do", // param name must be 'term'
        minLength: 3
    });
});
</script>



<form action="/search.do" method="get" class="form-horizontal" role="form">
    <div class="form-group">
        <input type="text" class="form-control" placeholder="Search" name="search_query" id="search_query" autocomplete="off" />
        <input type="submit" class="btn btn-default" name="submit" value="GO" />
    </div>
    <div>
        <input type="radio" name="area" value="all" checked />All
        <input type="radio" name="area" value="documents" />Documents
        <input type="radio" name="area" value="peers" />Peers
        <%--<input type="radio" name="area" value="groups" />Groups--%>
        <%--&lt;%&ndash;<input type="radio" name="area" value="tags" />Tags&ndash;%&gt;--%>
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

<%@ include file="/view/includes/static/footer.jsp" %>