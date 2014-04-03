<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<section id="signup">
    <div class="container">
        <div class="row text-center">
            <h2 id="msg-hey">Hey,</h2>
            <h1 id="msg-jumpstart">Let's jump start your career</h1>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form class="form-inline" name="register" id="register" action="/register" method="post" role="form" style="text-align:center;">

                    <div class="form-group">
                        <input type="text" class="form-control parsley-validated" name="user_name" id="valid_username"
                           placeholder="Choose a Username" style="width:300px;" parsley-trigger="change keyup">
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control parsley-validated" name="password" id="inputPassword"
                               placeholder="Choose a password" style="width:300px;" parsley-trigger="change keyup">
                    </div>

                    <div class="form-group">
                        <input type="email" class="form-control parsley-validated" name="email" id="inputEmail"
                               placeholder="Your email" style="width:300px;" parsley-trigger="change keyup">
                    </div>

                    <input type="hidden" name="form" value="index">


                    <div class="form-group">
                        <button type="submit" class="btn btn-default btn-success" style="width:300px;">Sign up for Peerpen</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="resume-large">
        <img data-0="top:760px" data-500="top:400px" data-650="top:1000px" src="/assets/images/index/resume-large.png"
        alt=""/>
    </div>
</section>

<section id="cloud">

    <div class="container">
        <div class="row text-center">
            <p data-400="opacity:0" data-600="opacity:1">
                Edit, publish, and store all your important documents in the cloud
            </p>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div data-430="top:300px" data-640="top:100px" class="float-glyphs" id="resume-medium">
                    <img src="/assets/images/index/resume-long.png" alt=""/>
                </div>
            </div>
            <div class="col-md-8">
                <div data-460="top:300px" data-630="top:120px" class="float-glyphs" id="resume-cloud">
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
                <p data-950="opacity:0" data-1150="opacity:1">
                    Receive updates for marked, and corrected personal document in the cloud whereever you are
                </p>
            </div>
            <div class="col-md-8">
                <div data-1000="opacity:0;" data-1200="opacity:1;" class="float-glyphs" id="imac-container">
                    <img id="imac" src="/assets/images/index/imac.png" alt=""/>
                    <img id="imac-browser" src="/assets/images/index/browser-imac.png" alt=""/>
                    <img data-1200="top:243px" data-1300="top:157px" id="imac-resume"
                         src="/assets/images/index/imac-resume-medium.png"
                         alt=""/>
                </div>
                <div data-1050="opacity:0;" data-1250="opacity:1;" class="float-glyphs" id="macbook-container">
                    <img data-1200="top:450px" data-1300="top:330px" id="macbook-resume"
                         src="/assets/images/index/resume.png" alt=""/>
                    <img id="macbook" src="/assets/images/index/macbook.png" alt=""/>
                    <img id="macbook-browser" src="/assets/images/index/browser-macbook.png" alt=""/>
                </div>
                <img id="mac-blocker" src="/assets/images/index/blocker.png" alt=""/>
            </div>
        </div>
    </div>
</section>

<section id="handheld">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div data-1600="left:-700px" data-1750="left:100px" class="float-glyphs" id="galaxy">
                    <img src="/assets/images/index/galaxy-s4.png" alt=""/>

                    <div class="float-glyphs" id="galaxy-screen">
                        <img src="/assets/images/index/galaxy-s4-screen.png" alt=""/>
                    </div>
                </div>
                <div data-1650="left:-700px" data-1850="left:50px" class="float-glyphs" id="iphone">
                    <img src="/assets/images/index/iphone5.png" alt=""/>

                    <div class="float-glyphs" id="iphone-screen">
                        <img src="/assets/images/index/iphone5-screen.png" alt=""/>
                    </div>
                </div>
            </div>

            <div data-1550="opacity:0" data-1650="opacity:1" class="col-md-4">
                <p>Conveniently available and accessible on your mobile devices</p>
            </div>
        </div>
    </div>
</section>

<%--<section id="osx-notification">--%>
    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<div class="col-md-4">--%>
                <%--<p data-2250="opacity:0" data-2500="opacity:1">--%>
                    <%--Delivered right to your desktop via advance web notification--%>
                <%--</p>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="col-md-4 col-md-offset-8">--%>
                <%--<div data-2170="top:-300px" data-2490="top:-120px" class="float-glyphs" id="osx-desktop-notification">--%>
                    <%--<img src="/assets/images/index/osx-notification.png" alt=""/>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</section>--%>

<%--<section id="twitter">--%>
    <%--<div class="row text-center">--%>
        <%--<p>We don't like to brag about us</p>--%>

        <%--<p>So here are praises from your friendly undergrads just like you</p>--%>
    <%--</div>--%>
    <%--<div class="container">--%>
        <%--<div id="carousel-tweets" class="carousel slide" data-ride="carousel">--%>
            <%--<ol class="carousel-indicators">--%>
                <%--<% int TWEETS_PER_SLIDE = 3; %>--%>
                <%--<% List<Status> tweets = (List<Status>) request.getAttribute( "tweets" ); %>--%>
                <%--<% int indicatorCount = tweets.size() / TWEETS_PER_SLIDE; %>--%>
                <%--<% for ( int i = 0; i < indicatorCount; i++ ) { %>--%>
                <%--<li data-target="#carousel-tweets" data-slide-to="<%= i %>" <%= (i == 0) ? "class=\"active\"" :--%>
                        <%--"" %>></li>--%>
                <%--<% } %>--%>
            <%--</ol>--%>
            <%--<div class="carousel-inner">--%>
                <%--<% for ( int i = 0; i < tweets.size() - (tweets.size() % 3); i += TWEETS_PER_SLIDE ) { %>--%>
                <%--<div class="item <%= (i==0) ? "active" : "" %>">--%>
                    <%--<div class="row">--%>
                        <%--<% for ( int j = i; j < i + TWEETS_PER_SLIDE; j++ ) { %>--%>
                        <%--<div class="col-md-4">--%>
                            <%--<blockquote>--%>
                                <%--<% Status tw = tweets.get( j ); %>--%>
                                <%--<p><%= StringEscapeUtils.escapeHtml4( tw.getText() ) %>--%>
                                <%--</p>--%>
                                <%--<a href="https://twitter.com/peerpen/status/<%= tw.getId() %>">--%>
                                    <%--<small class="pull-right">@<%= tw.getUser().getName() %>--%>
                                    <%--</small>--%>
                                <%--</a>--%>
                            <%--</blockquote>--%>
                        <%--</div>--%>
                        <%--<% } %>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<% } %>--%>
            <%--</div>--%>
            <%--<a class="left carousel-control" href="#carousel-tweets" data-slide="prev">--%>
                <%--<i class="fa fa-chevron-left fa-inverse"></i>--%>
            <%--</a>--%>
            <%--<a class="right carousel-control" href="#carousel-tweets" data-slide="next">--%>
                <%--<i class="fa fa-chevron-right fa-inverse"></i>--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</section>--%>

<%--<section id="counter">--%>
    <%--<div class="container">--%>
        <%--<div class="row text-center">--%>
            <%--<p>Convinced yet?</p>--%>
        <%--</div>--%>

        <%--<div class="row text-center">--%>
            <%--<div class="col-md-12 odometer">654321</div>--%>
            <%--<p>people have already joined,</p>--%>

            <%--<p>will you be the next one?</p>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</section>--%>

<!-- /container -->
<%@ include file="/view/includes/static/footer.jsp" %>

