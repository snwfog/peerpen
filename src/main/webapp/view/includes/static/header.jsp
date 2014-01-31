<!DOCTYPE html>
<html>
<%@ page language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Peerpen social web application for improving your resume and cover letter.">
    <meta name="author" content="Capstone Sunny Delight 2014">

    <title>
        <%--Last thing should put default title--%>
        <% String defaultTitle =  "PeerPen - A Capstone project by Sunny Delight";%>
        <%= request.getParameter("title") == null? "" : request.getParameter("title")%>
        <%= request.getAttribute("title") == null? defaultTitle : request.getAttribute("title")%>
    </title>

    <link rel="stylesheet" href="/assets/css/lib/bootplus.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/bootplus-responsive.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/custom/style.min.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/custom/peerpen.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/imgareaselect-animated.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/imgareaselect-default.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/imgareaselect-deprecated.css" type="text/css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <%--<link rel="stylesheet" href="/assets/css/lib/bootstrap.min.css" type="text/css">--%>
    <%--<link rel="stylesheet" href="/assets/css/lib/flat-ui.css" type="text/css">--%>
    <link rel="stylesheet" href="/assets/css/lib/jquery.tagit.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/lib/tagit.ui-zendesk.css" type="text/css">


    <!-- dont put the following to footer -->
    <script src="/assets/js/lib/jquery-1.9.1.js"></script>      <!-- for search autocomplete -->
    <script src="/assets/js/lib/jquery-ui-1.10.4.js"></script>  <!-- for search autocomplete -->
    <script src="/assets/js/lib/tag-it.js"></script>            <!-- this has to be in <head> -->



    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <!--<script src="../assets/js/html5shiv.js"></script>-->
    <![endif]-->

    <!-- Fav and touch icons -->
    <%--<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">--%>
    <%--<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">--%>
    <%--<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">--%>
    <%--<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">--%>
    <%--<link rel="shortcut icon" href="../assets/ico/favicon.png">--%>


</head>
<body>
