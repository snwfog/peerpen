<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<%@ page import="com.peerpen.model.Taggable" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>
<script src="/assets/js/custom/tag_autocomplete_caller.js"></script>

<div class="container">
    <h1>Tag Cloud</h1>

    <form action="/tag_search.do" method="get" class="form-horizontal" role="form">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Tag" name="tag_query" id="tag_query" autocomplete="off" />
            <input type="submit" class="btn btn-default" name="submit" value="Search" />
        </div>
    </form>

    <%
        Map<String, Object> map = new HashMap<String, Object>();
        List<TagDescriptor> tagDescriptors = new TagDescriptor().findAll( map );
        for(int i=0;i<tagDescriptors.size();i++){
            TagDescriptor td = tagDescriptors.get( i );
            %>
    <a href="/tag_search.do?term=<%= td.getTagName() %>"><code><%= td.getTagName() %></code></a>
    <%
        }
    %>

 </div>



<br /><br /><br />

<h1>TESTING:</h1>

<%

    //Get a list of all taggables
    //List<Taggable> taggables = new Taggable(  ).findAll( map );
    //for(int i=0; i<taggables.size();i++){
    //    System.out.println( "listing all taggable item: " + taggables.get( i ).getId());
    //}

    // proper way to attach a tag to entity:
    //1) get td
    //TagDescriptor td = new TagDescriptor(  ).getTagDescriptor( "blabla" );

    //2) entity.addTag(td)
    //Group g = new Group().find(3);
    //g.addTag(td);


    // find a taggable item from tag name
    //Taggable taggable = new Taggable(  );

%>



<%@ include file="/view/includes/static/footer.jsp" %>