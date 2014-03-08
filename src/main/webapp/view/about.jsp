<%@ include file="/view/includes/static/header.jsp" %>

<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>

<style>


    /* GLOBAL STYLES
    -------------------------------------------------- */
    /* Padding below the footer and lighter body text */

    body {
        padding-bottom: 40px;
        background-color: #fff;
        /*color: #5a5a5a;*/
    }

    /* CUSTOMIZE THE CAROUSEL
    -------------------------------------------------- */

    /* Carousel base class */
    .carousel {
        margin-bottom: 60px;
        margin-top: -100px;
    }

    .carousel .container {
        position: relative;
        z-index: 9;
    }

    .carousel-control {
        height: 80px;
        margin-top: 0;
        font-size: 120px;
        text-shadow: 0 1px 1px rgba(0,0,0,.4);
        background-color: transparent;
        border: 0;
        z-index: 10;
    }

    .carousel .item {
        height: 500px;
    }
    .carousel img {
        position: absolute;
        top: 0;
        left: 0;
        min-width: 100%;
        height: 500px;
    }

    .carousel-caption {
        background-color: transparent;
        position: static;
        max-width: 550px;
        padding: 0 20px;
        margin-top: 200px;
    }
    .carousel-caption h1,
    .carousel-caption .lead {
        margin: 0;
        line-height: 1.25;
        color: #fff;
        text-shadow: 0 1px 1px rgba(0,0,0,.4);
    }
    .carousel-caption .btn {
        margin-top: 10px;
    }

    /* MARKETING CONTENT
    -------------------------------------------------- */

    /* Center align the text within the three columns below the carousel */
    .marketing .span4 {
        text-align: center;
    }
    .marketing h2 {
        font-weight: normal;
    }
    .marketing .span4 p {
        margin-left: 10px;
        margin-right: 10px;
    }
    /* Featurettes
    ------------------------- */

    .featurette-divider {
        margin: 0px 0px 15px 0px; /* Space out the Bootstrap <hr> more */
    }
    .featurette {
        padding-top: 120px; /* Vertically center images part 1: add padding above and below text. */
        overflow: hidden; /* Vertically center images part 2: clear their floats. */
    }
    .featurette-image {
        margin-top: -120px; /* Vertically center images part 3: negative margin up the image the same amount of the padding to center it. */
    }

    /* Give some space on the sides of the floated elements so text doesn't run right into it. */
    .featurette-image.pull-left {
        margin-right: 40px;
    }
    .featurette-image.pull-right {
        margin-left: 40px;
    }

    /* Thin out the marketing headings */
    .featurette-heading {
        font-size: 35px;
        font-weight: 300;
        line-height: 1;
        letter-spacing: -1px;
    }

    /* RESPONSIVE CSS
    -------------------------------------------------- */

    @media (max-width: 979px) {

        .container.navbar-wrapper {
            margin-bottom: 0;
            width: auto;
        }
        .navbar-inner {
            border-radius: 0;
            margin: -20px 0;
        }

        .carousel .item {
            height: 500px;
        }
        .carousel img {
            width: auto;
            height: 500px;
        }

        .featurette {
            height: auto;
            padding: 0;
        }
        .featurette-image.pull-left,
        .featurette-image.pull-right {
            display: block;
            float: none;
            max-width: 40%;
            margin: 0 auto 20px;
        }
    }

    @media (max-width: 767px) {

        .carousel {
            margin-left: -20px;
            margin-right: -20px;
        }

        .carousel .item {
            height: 300px;
        }
        .carousel img {
            height: 300px;
        }
        .carousel-caption {
            width: 65%;
            padding: 0 70px;
            margin-top: 100px;
        }
        .carousel-caption h1 {
            font-size: 30px;
        }
        .carousel-caption .lead{
            font-size: 18px;
        }

        .marketing .span4 + .span4 {
            margin-top: 40px;
        }

        .featurette-heading {
            font-size: 30px;
        }
        .featurette .lead {
            font-size: 18px;
            line-height: 1.5;
        }

    }

    .dropdown-menu {
        background-color: #DBDBDB;
    }

    .col-md-1 {
        padding:0px 15px 0px 15px;
        display:inline-block;
    }
</style>

<!-- Carousel
================================================== -->

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="wrap-container main-presentation">
    <!-- Example row of columns -->
    <div class="founder-cover">
        <img class="featurette-image pull-left" src="/assets/images/developers/petercover-re.jpg" >
        <h2 class="featurette-heading founder-title">
            <img src="/assets/images/logo55x55.png"><br />
            Peter Trang
            <span class="muted founder-head-master">Young Visionary</span></h2>
        <p class="lead founder-header"><i>"Peerpen’s vision is to change the way people share advice"</i>
            <br /><br />
            PeerPen is a socially driven community that allows its members to correct resumes and cover letters by providing feedback and advice on an interactive platform. It revolves around a social and dynamic environment with an intuitive web interface, user friendly functions, and provides an intrinsic satisfaction through collaboration.
        </p></div>
</div>

<h1 class="team-title">THE TEAM</h1>

<div class="container-fluid team-container">
    <div class="row team-nav">

        <div class="col-md-1 team-clear-block">
        </div>

        <div id="profile1" class="col-md-1">
            <a href="#" onclick="selectProfile(1, true)"><img src="/assets/images/developers/wais-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Wais</span>
        </div>

        <div id="profile2" class="col-md-1">
            <a href="#" onclick="selectProfile(2, true)"><img src="/assets/images/developers/mike-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Mike</span>
        </div>

        <div id="profile3" class="col-md-1">
            <a href="#" onclick="selectProfile(3, true)"><img src="/assets/images/developers/bobby-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Bobby</span>
        </div>

        <div id="profile4" class="col-md-1">
            <a href="#" onclick="selectProfile(4, true)"><img src="/assets/images/developers/charles-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Charles</span>
        </div>

        <div id="profile5" class="col-md-1">
            <a href="#" onclick="selectProfile(5, true)"><img src="/assets/images/developers/saud-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Saud</span>
        </div>

        <div id="profile6" class="col-md-1">
            <a href="#" onclick="selectProfile(6, true)"><img src="/assets/images/developers/quang-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Quang</span>
        </div>

        <div id="profile7" class="col-md-1">
            <a href="#" onclick="selectProfile(7, true)"><img src="/assets/images/developers/harry-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Harry</span>
        </div>

        <div id="profile8" class="col-md-1">
            <a href="#" onclick="selectProfile(8, true)"><img src="/assets/images/developers/joe-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Joe</span>
        </div>

        <div id="profile9" class="col-md-1">
            <a href="#" onclick="selectProfile(9, true)"><img src="/assets/images/developers/robson-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Robson</span>
        </div>

        <div id="profile10" class="col-md-1">
            <a href="#" onclick="selectProfile(10, true)"><img src="/assets/images/developers/sl-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Su Long</span>
        </div>

        <div id="profile11" class="col-md-1">
            <a href="#" onclick="selectProfile(11, true)"><img src="/assets/images/developers/michelle-re.jpg" class="team-prof-thumb"></a><br />
            <span class="team-prof-name">Michelle</span>
        </div>


    </div>
</div>

<div class="container team-body-container">

    <div id="profile-body1">
        <h2 class="featurette-heading">Wais<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body2">
        <h2 class="featurette-heading">Mike<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body3">
        <h2 class="featurette-heading">Bobby<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body4">
        <h2 class="featurette-heading">Charles<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body5">
        <h2 class="featurette-heading">Saud<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body6">
        <h2 class="featurette-heading">Quang<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body7">
        <h2 class="featurette-heading">Harry<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body8">
        <h2 class="featurette-heading">Joe<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body9">
        <h2 class="featurette-heading">Robson<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body10">
        <h2 class="featurette-heading">Su Long<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

    <div id="profile-body11">
        <h2 class="featurette-heading">Michelle<br /><span class="muted">Software Engineer</span></h2>
        <br />
        <p class="lead team-body-text">
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</p>
    </div>

</div>

<p id="back-top">
    <a href="#top"><span></span>Back to Top</a>
</p>
</div><!-- /.container -->

<script src="/assets/js/lib/jquery-1.9.1.js"></script>
<script>
    function selectProfile(ID, scrolled) {
        resetProfile();

        document.getElementById("profile" + ID).className = "col-md-1 au-profile-selected";
        document.getElementById("profile-body" + ID).className = "au-profile-body-selected";

        if(scrolled) scrollToProfile(ID);
    }

    function scrollToProfile(ID) {
        $(document.body).animate({
            'scrollTop':   $('#profile' + ID).offset().top
        }, 1500);
    }

    function resetProfile() {

        var numProfiles = 11;

        for (var i=1; i<=numProfiles; i++)
        {
            document.getElementById("profile" + i).className = "col-md-1 au-profile-deselected";
            document.getElementById("profile-body" + i).className = "au-profile-body-deselected";
        }
    }

    $(document).ready(function(){
        // hide #back-top first
        $("#back-top").hide();

        resetProfile();
        selectProfile(1, false);

        // fade in #back-top
        $(function () {
            $(window).scroll(function () {
                if ($(window).scrollTop() >= $(document).height() - $(window).height() - 10) {
                    $('#back-top').fadeIn();
                } else {
                    $('#back-top').fadeOut();
                }
            });

            // scroll body to 0px on click
            $('#back-top a').click(function () {
                $('body,html').animate({
                    scrollTop: 0
                }, 800);
                return false;
            });
        });

    });
    !function ($) {
        $(function(){
            // carousel demo
            $('#myCarousel').carousel()
        })
    }(window.jQuery)
</script>

<%@ include file="/view/includes/static/footer.jsp" %>