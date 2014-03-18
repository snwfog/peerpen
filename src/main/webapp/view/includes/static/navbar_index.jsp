<div class="navbar navbar-inverse navbar-fixed-top nav-body" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="nav-logo-link"><img src="/assets/images/logo32x32.png"></a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <% String jspPageName = request.getServletPath();%>
                <li>
                    <a href="/about" class="nav-link">About</a>
                </li>
                <li  <%= jspPageName.contentEquals("/view/register.jsp")  ? "class=\"active\"" : "" %>>
                    <a href="/register" class="nav-link">Register</a>
                </li>
            </ul>

            <form class="navbar-form navbar-right" role="form" action="/login" method="post" parsley-validate>
                <div class="form-group">
                    <input class="span2 parsley-validated" id="index-username" type="text" placeholder="Username" name="username" parsley-trigger="change keyup" />
                </div>
                <div class="form-group">
                    <input class="span2 parsley-validated" id="index-password" type="password" placeholder="Password" name="password" parsley-trigger="change keyup" />
                </div>
                <button type="submit" class="btn btn-sm btn-warning">Sign in</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</div>
