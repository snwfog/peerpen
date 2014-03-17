<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="font-family:'Oswald', sans-serif; font-size:16px;">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" style="padding-top:5px; display:block;"><img src="/assets/images/logo32x32.png"></a>
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <% String jspPageName = request.getServletPath();%>
                <li>
                    <a href="/about" style="font-weight:300;">About</a>
                </li>
                <li  <%= jspPageName.contentEquals("/view/register.jsp")  ? "class=\"active\"" : "" %>>
                    <a href="/register" style="font-weight:300;">Register</a>
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
