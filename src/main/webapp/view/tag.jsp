<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<%@ page import="com.peerpen.model.Taggable" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.peerpen.model.Document" %>
<%@ include file="/view/includes/static/header.jsp" %>
<script src="/assets/js/custom/tag_autocomplete_caller.js"></script>


<div class="container">
    <h1>Tag Cloud</h1>

    <form action="/tag_search.do" method="get" class="form-horizontal" role="form">
        <div class="form-group">
            <div class="input-group">
                <input type="text" class="form-control" name="tag_query" id="tag_query" autocomplete="off" />
                <span class="input-group-btn" style="visibility: hidden">
                    <button type="submit" class="btn btn-primary" name="submit" />Search</button>
                </span>
            </div>
        </div>
    </form>


    <ul class="tagit ui-widget ui-widget-content ui-corner-all" style="border:0">
    <%
        Map<String, Object> map = new HashMap<String, Object>();
        List<TagDescriptor> tagDescriptors = new TagDescriptor().findAll( map );
        for(int i=0;i<tagDescriptors.size();i++){
            TagDescriptor td = tagDescriptors.get( i );
            %>
        <li class="tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable">
            <span class="tagit-label">
                <a href="/tag_search.do?tag_query=<%= td.getTagName() %>"><%= td.getTagName() %></a>
            </span>
        </li>
    <%
        }
    %>
    </ul>

    <hr />


    <!-- Handling search result -->
    <%
        if(session.getAttribute( "tagSearchResultsGroups" ) != null){
            ArrayList<Group> groups = (ArrayList<Group>) session.getAttribute( "tagSearchResultsGroups" );
            for(int i =0;i< groups.size();i++){
                Group group = groups.get( i );
                %>
                [Group] <a href="/group/<%= group.getId() %>"><%= group.getGroupName() %></a> - <i><%= group.getDescription() %></i><br />
                <%
            }
        }
    %>

    <%
        if(session.getAttribute( "tagSearchResultsDocuments" ) != null){
            ArrayList<Document> documents = (ArrayList<Document>) session.getAttribute( "tagSearchResultsDocuments" );
            for(int i =0;i< documents.size();i++){
                Document document = documents.get( i );
                %>
                [Document] <a href="/document/<%= document.getId() %>"> <%= document.getDocName() %></a>
                by: <a href="/peer/<%= document.getPeerId() %>"> <%= document.getPeer().getUserName() %></a>
                last modified: <%= document.getLastModifiedDate() %><br />
                <%
            }
        }
    %>
    <br /><br /><br /><br /><br />




    <hr />


    <%
        Group g = new Group(  ).find( 3 );
        List<TagDescriptor> tds = g.getTagDescriptors();
    %>
    Demo loading tags attached to Group <%=g.getGroupName()%>:
    <form action="/tag_search.do" method="post" class="form-horizontal" role="form">
        <ul id="entityTags">
        <%
            for (TagDescriptor td : tds){
        %>
            <li><%=td.getTagName() %></li>
        <%
            }
        %>
        </ul>
        <button type="submit" class="btn btn-primary" name="submit" />OK</button>
    </form>



 </div>
<%@ include file="/view/includes/static/footer.jsp" %>