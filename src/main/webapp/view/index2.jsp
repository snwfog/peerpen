<%@ include file="/view/includes/static/header.jsp" %>
<!-- Le styles -->
<%--<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>--%>
<%--<style type="text/css">--%>
<%--body {--%>
<%--padding-top: 46px;--%>
<%--padding-bottom: 40px;--%>
<%--}--%>

<%--.hero-unit {--%>
<%--background: #00001C url(../assets/img/cover4.jpg) no-repeat top left;--%>
<%--}--%>

<%--.hero-unit h1 {--%>
<%--color: #FFF--%>
<%--}--%>

<%--.hero-unit p {--%>
<%--color: #F5F5F5--%>
<%--}--%>
<%--</style>--%>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--<a class="brand" href="#">PeerPen<a>-->

            <div class="nav-collapse collapse">
                <a href="#"><img src="../assets/images/PPLogo-01.png" width="35px" style="float:left;padding-top:5px;"></a>
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>


                <form class="navbar-form pull-right" id="index-form" action="/login" method="post" parsley-validate>
                    <input class="span2 parsley-validated" id="index-username" type="text" placeholder="Username" name="username" parsley-trigger="change keyup"/>
                    <input class="span2 parsley-validated" id="index-password" type="password" placeholder="Password" name="password" parsley-trigger="change keyup"/>

                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>

                <ul class="nav pull-right">
                    <li class="dropdown">
                        <img src="../assets/images/myaccount.png" width="20px" style="float:left;padding-top:10px; padding-right:5px;"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <form class="navbar-form" id="index-form" action="/login" method="post" parsley-validate>
                                    Have an account? Sign in now<br />
                                    <input class="span2 parsley-validated" id="parsley-user" type="text" placeholder="Username" name="username" parsley-trigger="keyup change"/><br />
                                    <input class="span2 parsley-validated" id="parsley-pass" type="password" placeholder="Password" name="password" parsley-trigger="keyup change"><br />
                                    <button type="submit" class="btn btn-warning" style="float:right" id="index2">Sign in</button>
                                </form>
                            </li>
                            <li class="divider"></li>
                            <li style="text-align:center; text-transform:none">No account? <a href="#" style="text-transform:uppercase; font-weight:bold; color:#fb5324">Sign up now</a></li>
                        </ul>
                    </li>
                </ul>

            </div>
            <div id="errorContainer">
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
    <div class="container">
        <h1>Your career is our business</h1>

        <p>This is a template for a simple marketing or informational website. It includes a large callout called the
            hero unit and three supporting pieces of content. Use it as a starting point to create something more
            unique.</p>

        <p><a href="#" class="btn-learn-more">Learn more &raquo;</a></p>
    </div>
</div>


<div class="container">


    <!-- Example row of columns -->
    <div class="row">
        <div class="span4">

            <div class="card">
                <img src="../assets/images/collab.png" style="height:125px; margin:auto; display:block;">
                <h2 class="card-heading simple">Collaboration</h2>

                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

                    <p>
                </div>
            </div>

        </div>
        <div class="span4">

            <div class="card">
                <img src="../assets/images/interac.png" style="height:125px; margin:auto; display:block;">
                <h2 class="card-heading simple">Interactive</h2>

                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

                    <p>
                </div>
            </div>

        </div>
        <div class="span4">

            <div class="card">
                <img src="../assets/images/realtime.png" style="height:125px; margin:auto; display:block;" height="100px">
                <h2 class="card-heading simple">Real-Time</h2>

                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

                    <p>
                </div>
            </div>

        </div>
    </div>
</div>

<footer style="background-color:#cacaca; padding:25px; margin-top:50px; text-align:center;">
    <p style="width:100%; margin:0px; color:#333;">&copy; Company 2013</p>
</footer>

</div>
<!-- /container -->
<%@ include file="/view/includes/static/footer.jsp" %>

