<%@ include file="/view/includes/static/header.jsp" %>

<div class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li class="divider"></li>
                    <li><a href="#">One more separated link</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Link</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</div>

<div class="container">
    <div class="row text-center">
        <p>Welcome to Peerpen</p>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-6">
            <form class="form-horizontal" name="register" id="register" action="/register" method="post" role="form">
                <div class="form-group">
                    <label for="lastName" class="col-sm-2 control-label">Last Name</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="last_name" id="lastName" placeholder="Last Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">First Name</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="first_name" id="firstName"
                               placeholder="First Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userName" class="col-sm-2 control-label">Username</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="user_name" id="userName"
                               placeholder="Username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-10">
                        <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-2 control-label">Password</label>

                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password" id="inputPassword"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="col-sm-2 control-label">Confirm Password</label>

                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="confirm_password" id="confirmPassword"
                               placeholder="Confirm Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2" id="resume-regular-size-top">
            <h1 id="owner-name">Charles Yang</h1>
            <section>
                <p>
                    Mauris consectetur varius magna nec tempus. Praesent ut porttitor lacus. Sed vitae tempus risus.
                    Aenean ac ligula orci. Praesent sed ante neque. Phasellus eu nunc at nibh bibendum vulputate. Duis
                    scelerisque non sem id placerat. Nam quis dictum lectus. Nunc ac turpis vel risus suscipit rutrum
                    nec vel libero. Donec ultricies, erat vel ultrices volutpat, turpis tortor condimentum turpis,
                    vel volutpat tortor diam at tellus. Sed ut pretium lectus, a dictum ante. Sed congue varius
                    hendrerit.
                </p>
            </section>

        </div>
    </div>
</div>

<section id="cloud">
    <div class="row text-center">
        <p>Edit, publish, and store all your important documents in the cloud</p>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="float-glyphs" id="resume-medium">
                <img src="/assets/images/index/resume-long.png" alt=""/>
            </div>
        </div>
        <div class="col-md-8">
            <div class="float-glyphs" id="resume-cloud">
                <img src="/assets/images/index/cloud.png" alt=""/>
            </div>
        </div>
    </div>
</section>

<section id="portable">
    <div class="row">
        <div class="col-md-4">
            <p>Receive updates for marked, and corrected personal document in the cloud anywhere you go.</p>
        </div>
        <div class="col-md-8">
            <div class="float-glyphs" id="imac">
                <img src="/assets/images/index/imac.png" alt=""/>
                <div class="float-glyphs" id="imac-browser">
                    <img src="/assets/images/index/browser-imac.png" alt=""/>
                </div>
            </div>
            <div class="float-glyphs" id="macbook">
                <img class="img-responsive" src="/assets/images/index/macbook.png" alt=""/>
                <div class="float-glyphs" id="macbook-browser">
                    <img src="/assets/images/index/browser-macbook.png" alt=""/>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="handheld">
    <div class="row">
        <div class="col-md-8">
            <div class="float-glyphs" id="galaxy">
                <img src="/assets/images/index/galaxy-s4.png" alt=""/>
                <div class="float-glyphs" id="galaxy-screen">
                    <img src="/assets/images/index/galaxy-s4-screen.png" alt=""/>
                </div>
            </div>
            <div class="float-glyphs" id="iphone">
                <img src="/assets/images/index/iphone5.png" alt=""/>
                <div class="float-glyphs" id="iphone-screen">
                    <img src="/assets/images/index/iphone5-screen.png" alt=""/>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <p>Conveniently available and accessible on your mobile devices</p>
        </div>
    </div>
</section>

<section id="osx-notification">
    <div class="row">
        <div class="col-md-4">
            <p>Advanced OSX Maverick web notification</p>
        </div>
    </div>
    <div class="float-glyphs" id="osx-desktop-notification">
        <img src="/assets/images/index/osx-notification.png" alt=""/>
    </div>
</section>

<section id="twitter">
    <p>Fusce malesuada, tellus vitae euismod lacinia, tellus dui accumsan enim, suscipit malesuada magna elit sed leo. Cras nunc ipsum, vulputate vel augue at, pharetra ultricies ipsum. Pellentesque elementum velit vel posuere vestibulum. Mauris libero massa, scelerisque quis ultricies sit amet, laoreet non mauris. Vestibulum euismod non urna a tristique. Quisque mollis, nulla nec commodo convallis, orci dui imperdiet risus, in iaculis mi eros vel erat. Etiam non cursus nibh, sit amet congue turpis.</p>
</section>

<section id="counter">
    <div class="container">
        <div class="row text-center">
            <p>Convinced yet?</p>
        </div>

        <div class="row text-center">
            <div class="col-md-12" id="odometer"></div>
            <p>people have already joined,</p>
            <p>will you be the next one?</p>
            <h1>Let us jump start your career</h1>
        </div>
    </div>
</section>




<footer>

</footer>

<!-- /container -->
<%@ include file="/view/includes/static/footer.jsp" %>

