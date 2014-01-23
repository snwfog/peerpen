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
            <a class="brand" href="#">PeerPen<a>

            <div class="nav-collapse collapse">
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

                <form class="navbar-form pull-right" action="/login" method="post">
                    <input class="span2" type="text" placeholder="Username" name="username">
                    <input class="span2" type="password" placeholder="Password" name="password">
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
    <div class="container">
        <h1>Hello, world!</h1>

        <p>This is a template for a simple marketing or informational website. It includes a large callout called the
            hero unit and three supporting pieces of content. Use it as a starting point to create something more
            unique.</p>

        <p><a href="#" class="btn btn-primary btn-large">Learn more &raquo;</a></p>
    </div>
</div>


<div class="container">


    <!-- Example row of columns -->
    <div class="row">
        <div class="span4">

            <div class="card">
                <h3 class="card-heading simple">Simple News Card</h3>

                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

                    <p>
                </div>
                <div class="card-actions">
                    <button class="btn btn-block">View Details</button>
                </div>
            </div>

        </div>
        <div class="span4">

            <div class="card">
                <h3 class="card-heading simple">Simple News Card</h3>

                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

                    <p>
                </div>
                <div class="card-actions">
                    <button class="btn btn-block">View Details</button>
                </div>
            </div>

        </div>
        <div class="span4">

            <div class="card">
                <h3 class="card-heading simple">Simple News Card</h3>

                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

                    <p>
                </div>
                <div class="card-actions">
                    <button class="btn btn-block">View Details</button>
                </div>
            </div>

        </div>
    </div>

    <footer>
        <p>&copy; Company 2013</p>
    </footer>

</div>
<!-- /container -->
<%@ include file="/view/includes/static/footer.jsp" %>

