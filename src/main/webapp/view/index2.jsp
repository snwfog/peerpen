<%@ page import="twitter4j.Status" %>
<%@ page import="java.util.List" %>
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

<section id="signup">
    <div class="container">
        <div class="row text-center">
            <h1>Let's jump start your career</h1>
        </div>
        <div class="row">
            <div class="col-md-6 col-md-offset-6">
                <form class="form-horizontal" name="register" id="register" action="/register" method="post"
                      role="form">
                    <div class="form-group">
                        <label for="lastName" class="col-sm-2 control-label">Last Name</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="last_name" id="lastName"
                                   placeholder="Last Name">
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
</section>

<section id="resume-large">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 resume-regular">
                <h1 id="owner-name">Charles Yang</h1>
                <section>
                    <p>
                        Mauris consectetur varius magna nec tempus. Praesent ut porttitor lacus. Sed vitae tempus risus.
                        Aenean ac ligula orci. Praesent sed ante neque. Phasellus eu nunc at nibh bibendum vulputate.
                        Duis
                        scelerisque non sem id placerat. Nam quis dictum lectus. Nunc ac turpis vel risus suscipit
                        rutrum
                        nec vel libero. Donec ultricies, erat vel ultrices volutpat, turpis tortor condimentum turpis,
                        vel volutpat tortor diam at tellus. Sed ut pretium lectus, a dictum ante. Sed congue varius
                        hendrerit.
                    </p>
                </section>

            </div>
        </div>
    </div>
</section>

<section id="cloud">
    <div class="container">
        <div class="row text-center">
            <p data-990="opacity:0" data-1100="opacity:1">
                Edit, publish, and store all your important documents in the cloud
            </p>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div data-1100="top:300px" data-1400="top:100px" class="float-glyphs" id="resume-medium">
                    <img src="/assets/images/index/resume-long.png" alt=""/>
                </div>
            </div>
            <div class="col-md-8">
                <div data-1100="top:300px" data-1400="top:120px" class="float-glyphs" id="resume-cloud">
                    <img src="/assets/images/index/cloud.png" alt=""/>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="portable">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <p data-1600="opacity:0" data-1750="opacity:1">
                    Receive updates for marked, and corrected personal document in the cloud whereever you are
                </p>
            </div>
            <div class="col-md-8">
                <div data-1620="opacity:0;" data-1900="opacity:1;" class="float-glyphs" id="imac">
                    <img src="/assets/images/index/imac.png" alt=""/>

                    <div class="float-glyphs" id="imac-browser">
                        <img src="/assets/images/index/browser-imac.png" alt=""/>
                    </div>
                </div>
                <div data-1600="opacity:0;" data-1900="opacity:1;" class="float-glyphs" id="macbook">
                    <img class="img-responsive" src="/assets/images/index/macbook.png" alt=""/>

                    <div class="float-glyphs" id="macbook-browser">
                        <img src="/assets/images/index/browser-macbook.png" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="handheld">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div data-2050="left:-700px" data-2350="left:100px" class="float-glyphs" id="galaxy">
                    <img src="/assets/images/index/galaxy-s4.png" alt=""/>

                    <div class="float-glyphs" id="galaxy-screen">
                        <img src="/assets/images/index/galaxy-s4-screen.png" alt=""/>
                    </div>
                </div>
                <div data-2050="left:-500px" data-2350="left:50px" class="float-glyphs" id="iphone">
                    <img src="/assets/images/index/iphone5.png" alt=""/>

                    <div class="float-glyphs" id="iphone-screen">
                        <img src="/assets/images/index/iphone5-screen.png" alt=""/>
                    </div>
                </div>
            </div>

            <div data-2050="opacity:0" data-2475="opacity:1" class="col-md-4">
                <p>Conveniently available and accessible on your mobile devices</p>
            </div>
        </div>
    </div>
</section>

<section id="osx-notification">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <p data-2600="opacity:0" data-2770="opacity:1">
                    Delivered right to your desktop via advance web notification
                </p>
            </div>
        </div>
        <div class="row">
            <div data-2770="right:-400px" data-2790="right:10px" class="float-glyphs" id="osx-desktop-notification">
                <img src="/assets/images/index/osx-notification.png" alt=""/>
            </div>
        </div>
    </div>
</section>

<section id="twitter">
    <div class="row text-center">
        <p>We don't like to brag about us</p>

        <p>So here are praises from your friendly undergrads just like you</p>
    </div>
    <div class="container">
        <div id="carousel-tweets" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <% int TWEETS_PER_SLIDE = 3; %>
                <% List<Status> tweets = (List<Status>) request.getAttribute( "tweets" ); %>
                <% int indicatorCount = tweets.size() / TWEETS_PER_SLIDE; %>
                <% for (int i = 0; i < indicatorCount; i++) { %>
                    <li data-target="#carousel-tweets" data-slide-to="<%= i %>" <%= (i == 0) ? "class=\"active\"" : "" %>></li>
                <% } %>
            </ol>
            <div class="carousel-inner">
                <% for (int i = 0; i < tweets.size() - (tweets.size() % 3); i += TWEETS_PER_SLIDE) { %>
                    <div class="item <%= (i==0) ? "active" : "" %>">
                        <div class="row">
                            <% for (int j = i; j < i + TWEETS_PER_SLIDE; j++) { %>
                                <div class="col-md-4">
                                    <blockquote>
                                        <% Status tw = tweets.get( j ); %>
                                        <p><%= StringEscapeUtils.escapeHtml4( tw.getText() ) %></p>
                                        <a href="https://twitter.com/peerpen/status/<%= tw.getId() %>">
                                            <small class="pull-right">@<%= tw.getUser().getName() %></small>
                                        </a>
                                    </blockquote>
                                </div>
                            <% } %>
                        </div>
                    </div>
                <% } %>
            </div>
            <a class="left carousel-control" href="#carousel-tweets" data-slide="prev">
                <i class="fa fa-chevron-left fa-inverse"></i>
            </a>
            <a class="right carousel-control" href="#carousel-tweets" data-slide="next">
                <i class="fa fa-chevron-right fa-inverse"></i>
            </a>
        </div>
    </div>
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

            <h1>Your career is in good hands</h1>
        </div>
    </div>
</section>

<section id="resume-tail">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 resume-regular">
                <p>Reference available upon request</p>
            </div>
        </div>
    </div>
</section>


<footer>

</footer>

<!-- /container -->
<%@ include file="/view/includes/static/footer.jsp" %>

