<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ page import="com.peerpen.model.Group" %>
<%@ include file="/view/includes/static/header.jsp" %>


<form action="search.do" method="get" align="center">
    Search <input type="text" name="query" />
    <input type="submit" name="submit" value="OK" />
    <br />
    <%--<input type="radio" name="area" value="all" checked />All--%>
    <input type="radio" name="area" value="documents" checked />Documents
    <input type="radio" name="area" value="peers" />Peers
    <input type="radio" name="area" value="groups" />Groups
    <%--<input type="radio" name="area" value="tags" />Tags--%>
</form>



<!-- Handling search result -->
<%--${searchResults}--%>
<%
    if(session.getAttribute( "searchResults" ) != null){
        String area = session.getAttribute( "searchArea" ).toString();

        // each result item has its own look based on the object type
        if(area.equals( "documents" )){
            ArrayList<Document> results = (ArrayList<Document>) session.getAttribute( "searchResults" );
            for(int i=0;i<results.size();i++){
            %>
                [Document] <a href="/document/<%= results.get( i ).getId() %>"> <%= results.get( i ).getDocName() %></a>
                by: <a href="/peer/<%= results.get(i).getPeerId() %>"> <%= results.get( i ).getPeer().getUserName() %></a>
                last modified: <%= results.get(i).getLastModifiedDate() %><br />
            <%
            }
        }else if(area.equals( "peers" )){
            ArrayList<Peer> results = (ArrayList<Peer>) session.getAttribute( "searchResults" );
            for(int i=0;i<results.size();i++){
            %>
                [Peer] <a href="/peer/<%= results.get( i ).getId() %>"> <%= results.get( i ).getUserName() %></a>
                (<%= results.get(i).getFirstName() %> <%= results.get( i ).getLastName() %>)
                point: <%= results.get(i).getPoint() %><br />
            <%
            }
        }else if(area.equals( "groups" )){
            ArrayList<Group> results = (ArrayList<Group>) session.getAttribute( "searchResults" );
            for(int i=0;i<results.size();i++){
            %>
                [Group] <a href="/group/<%= results.get( i ).getId() %>" alt="<%= results.get(i).getDescription() %>"> <%= results.get( i ).getGroupName() %></a><br />
            <%
            }
        }
    }else{
        out.println("No match.");
    }
%>

<%--<c:forEach items="${searchResults}" var="searchResult" begin="0" end="4">--%>
<%--<p>${searchResult}</p>--%>
<%--</c:forEach>--%>


<%@ include file="/view/includes/static/footer.jsp" %>