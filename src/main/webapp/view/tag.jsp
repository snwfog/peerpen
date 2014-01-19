<%@ page import="com.peerpen.model.Group" %>
<%@ page import="com.peerpen.model.TagDescriptor" %>
<%@ page import="com.peerpen.model.Taggable" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.peerpen.model.Tag" %>


tag testing page

<%

    Map<String, Object> map = new HashMap<String, Object>();


    // Get a list of all taggables
    List<Taggable> taggables = new Taggable(  ).findAll( map );
    for(int i=0; i<taggables.size();i++){
        System.out.println( "listing all taggable item: " + taggables.get( i ).getId());
    }

    // Get a list of all tag descriptors
    List<TagDescriptor> tagDescriptors = new TagDescriptor().findAll( map );
    for(int i=0;i<tagDescriptors.size();i++){
        System.out.println( "listing all tag descriptors: " + tagDescriptors.get( i ).getTagName() );
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