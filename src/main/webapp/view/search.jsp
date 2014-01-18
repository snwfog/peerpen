<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>

<script>
var autocomplete = true;
if(autocomplete){
$(document).ready(function() {
    $('#query').keyup(function(event)
    {
        var query = $('#query').val();
        $.post('autocomplete.do', {
            query: query
        }, function(responseJson) {
            var ul = $('#suggestion_list');    // locate the ul dom
            ul.empty();     // remove existing li's
            $.each(responseJson, function(key, value) {
                // Iterate over the JSON object.
                var li = $('<li id="suggest_item" style="display:block;text-align:left;padding:5px">').text( value );
                ul.append(li);
                li.mouseover(function(){
                    $(this).css("background-color","gray");
                });
                li.mouseout(function(){
                    $(this).css("background-color", "white");
                });
                li.click(function(){
                    $('#query').val(li.text());
                });
                if (event.keyCode == 40) { // down
                    //li.siblings().first().css("background-color","gray");
                }
            });
        });
    });
});
}
</script>



<div>
<form action="search.do" method="get" align="center">
    Search <input type="text" name="query" id="query" style="margin-bottom:0px;" autocomplete="off" />
    <input type="submit" name="submit" value="OK" />
    <br />
    <ul id="suggestion_list" style="background-color:white;width:200px;margin:auto"></ul>
    <%--<br />--%>
    <%--<input type="radio" name="area" value="all" checked />All--%>
    <%--<input type="radio" name="area" value="documents" />Documents--%>
    <%--<input type="radio" name="area" value="peers" />Peers--%>
    <%--<input type="radio" name="area" value="groups" />Groups--%>
    <%--&lt;%&ndash;<input type="radio" name="area" value="tags" />Tags&ndash;%&gt;--%>
</form>
</div>


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