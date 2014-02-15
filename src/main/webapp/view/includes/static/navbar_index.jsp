<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><img src="/assets/images/PPLogo-01.png" width="35px"></a>
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <% String jspPageName = request.getServletPath();%>
                <li>
                    <a href="/about">About</a>
                </li>
                <li  <%= jspPageName.contentEquals("/view/register.jsp")  ? "class=\"active\"" : "" %>>
                    <a href="/register">Register</a>
                </li>
            </ul>

            <form class="navbar-form navbar-right" role="form" style="margin:0 auto 0 auto;" action="/login" method="post" parsley-validate>
                <div class="form-group">
                    <input class="span2 parsley-validated" id="index-username" type="text" placeholder="Username" name="username" parsley-trigger="change keyup" />
                </div>
                <div class="form-group">
                    <input class="span2 parsley-validated" id="index-password" type="password" placeholder="Password" name="password" parsley-trigger="change keyup" />
                </div>
                <button type="submit" class="btn btn-sm btn-primary">Sign in</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</div>
