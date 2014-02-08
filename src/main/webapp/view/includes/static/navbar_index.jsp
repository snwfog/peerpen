<div class="navbar navbar-fixed-top">
    <div class="navbar-inverse">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--<a class="brand" href="#">PeerPen<a>-->
            <div class="collapse navbar-collapse">
                <a href="#"><img src="../assets/images/PPLogo-01.png" width="35px" style="float:left;padding-top:5px;"></a>
                <ul class="nav navbar-nav">
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

                <ul class="nav navbar-nav pull-right">
                    <a href="#"><img src="../assets/images/myaccount.png" width="20px" style="float:left;padding-top:10px; padding-right:5px;"></a>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <form class="navbar-form" id="" action="/login" method="post" parsley-validate>
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