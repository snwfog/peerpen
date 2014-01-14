<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="com.peerpen.controller.TagController" %>

<%

    String query = request.getParameter("q");
    System.out.println("here--->" + query);

    List<String> countries = TagController.getData( query );

    Iterator<String> iterator = countries.iterator();
    while(iterator.hasNext()) {
        String country = (String)iterator.next();
        out.println(country);
    }
%>