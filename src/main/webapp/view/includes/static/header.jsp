<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="org.codehaus.plexus.util.StringUtils" %>
<!DOCTYPE html>
<html>
<%@ page language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Peerpen social web application for improving your resume and cover letter.">
    <meta name="author" content="Capstone Sunny Delight 2014">

    <title>
        <%

            String jspPageNAme = request.getServletPath();
            Pattern regx = Pattern.compile("/[A-Za-z1-9]*\\.jsp");
            Matcher explode = regx.matcher(jspPageNAme);

            String defaultTitle = "PeerPen - A Capstone project by Sunny Delight";
            if ( request.getParameter( "title" ) != null ) {
              out.println("Peerpen - " +  request.getParameter( "title" ) );
            } else if ( request.getAttribute( "title" ) != null ) {
              out.println("Peerpen - " + request.getAttribute( "title" ) );
            } else if ( explode !=null && explode.find() ){
                if(explode.group(0) != null){
                  String formatIt = explode.group(0).replace(".jsp", "").replace("/", "");
                  out.println("Peerpen - " + StringUtils.capitaliseAllWords(formatIt));
                }
            } else {
                out.println( defaultTitle );
            }
        %>
    </title>
    <link rel="stylesheet" href="/assets/css/lib/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/odometer-theme-default.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/jquery.Jcrop.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/jquery.tagit.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/jquery-ui-1.10.4-smoothness.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/tagit.ui-zendesk.min.css" type="text/css">

    <link rel="stylesheet" href="/assets/css/custom/style.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/custom/avatar.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/custom/peerpen.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/custom/ppeditor.css" type="text/css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <!--<script src="../assets/js/html5shiv.js"></script>-->
    <!--[endif]-->

    <!-- Fav and touch icons -->
    <%--<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">--%>
    <%--<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">--%>
    <%--<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">--%>
    <%--<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">--%>
    <%--<link rel="shortcut icon" href="../assets/ico/favicon.png">--%>
</head>
<body>
