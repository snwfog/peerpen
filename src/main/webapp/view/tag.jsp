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


<%

    // Creating a taggable entity will write a record into taggable table (works)
    //Group g = new Group();
    //g.setGroupName( "mcgill" );
    //g.setDescription( "this is description of mcgill" );
    //g.save();

    // Attaching / detaching a tag to entity entity (works)
    //Group g = new Group(  ).find( 3 );
    //TagDescriptor td = new TagDescriptor(  ).getTagDescriptor( "university" );
    //g.addTag( td );
    //g.removeTag( td );

    // Find a list of taggable items from 1 tag name (works)
    //Map<String, Object> m = new HashMap<String, Object>();
    //m.put( "tagName", "concordia" );
    //List<Taggable> taggables = new Taggable().getTaggables( (TagDescriptor) new TagDescriptor().find( m ) );
    //if(taggables.size() > 0){
    //    for(int i=0;i<taggables.size();i++){
    //        Taggable t = taggables.get( i );
    //        System.out.println( "taggableid:" + t.getId() + " child_id:" + t.getChildId() + " type:" + t.getType() );
    //    }
    //}else{
    //    System.out.println("no taggable item with this tag");
    //}

%>




<%@ include file="/view/includes/static/footer.jsp" %>