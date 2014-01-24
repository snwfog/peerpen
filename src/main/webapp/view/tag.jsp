<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<%@ page import="com.peerpen.model.Taggable" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ include file="/view/includes/static/header.jsp" %>
<script src="/assets/js/custom/tag_autocomplete_caller.js"></script>

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



<br /><br /><br />

<h1>TESTING:</h1>

<%

    // Get a list of all taggables
    List<Taggable> taggables = new Taggable(  ).findAll( map );
    for(int i=0; i<taggables.size();i++){
        System.out.println( "listing all taggable item: " + taggables.get( i ).getId());
    }




    // saving a tag

    TagDescriptor td = new TagDescriptor(  );
    td.setTagName( "ccc" );
    td.save();
    // in case it already exists
    map.put("tagName", "ccc");
    td = new TagDescriptor(  ).find( map );



    Group g = new Group().find(1);
    //g.addTag(td);                // bug duplicate handling


    // remove a tag
    // todo


    // find a taggable item from tag name
    // todo


    // find the tagname first
    Map<String, Object> critera = new HashMap<String, Object>();
    critera.put( "tagName", "xxxx" );
    TagDescriptor t = new TagDescriptor(  ).find( critera );



%>