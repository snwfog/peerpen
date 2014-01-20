<%@ include file="/view/includes/static/header.jsp" %>
<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>

<style>


  /* GLOBAL STYLES
  -------------------------------------------------- */
  /* Padding below the footer and lighter body text */

body {
  padding-bottom: 40px;
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
  margin: 80px 0; /* Space out the Bootstrap <hr> more */
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
  font-size: 50px;
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
</style>

<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide">
  <div class="carousel-inner">
    <div class="item active">
      <img src="/assets/images/slide-01.jpg" alt="">
      <div class="container">
        <div class="carousel-caption">
          <h1>Peerpen</h1>
          <p class="lead">PeerPen is a socially driven community that allows its members to correct resumes and cover letters by providing feedback and advice on an interactive platform. It revolves around a social and dynamic environment with an intuitive web interface, user friendly functions, and provides an intrinsic satisfaction through collaboration.</p>
         </div>
      </div>
    </div>
    <div class="item">
      <img src="/assets/images/slide-02.jpg" alt="">
      <div class="container">
        <div class="carousel-caption">
          <h1>Vision</h1>
          <p class="lead">Peerpen&#39;s vision is to change the way people share advice</p>
        </div>
      </div>
    </div>
    <div class="item">
      <img src="/assets/images/slide-03.jpg" alt="">
      <div class="container">
        <div class="carousel-caption">
          <h1>Mission</h1>
          <p class="lead">Peerpen&#39;s core mission is to build the most complete online community where members will be able to be part of each other&#39;s success</p>
        </div>
      </div>
    </div>
  </div>
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div><!-- /.carousel -->

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">
  <div class="featurette">
    <img class="featurette-image pull-right" src="/assets/images/browser-icon-chrome.png">
    <h2 class="featurette-heading">First featurette headling. <span class="muted">It'll blow your mind.</span></h2>
    <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
  </div>

  <hr class="featurette-divider">

  <div class="featurette">
    <img class="featurette-image pull-left" src="/assets/images/browser-icon-firefox.png">
    <h2 class="featurette-heading">Vision<span class="muted">See for yourself.</span></h2>
    <p class="lead">Peerpen’s vision is to change the way people share advice</p>
  </div>

  <hr class="featurette-divider">

  <div class="featurette">
    <img class="featurette-image pull-right" src="/assets/images/browser-icon-safari.png">
    <h2 class="featurette-heading">And lastly, this one. <span class="muted">Checkmate.</span></h2>
    <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
  </div>

  <hr class="featurette-divider">
  <h2 class="featurette-heading">And lastly, this one. <span class="muted">Checkmate.</span></h2>
  <br />
  <br />
  <div class="row">
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/peter.jpg">
        <h2>Peter Trang</h2>
        <p>He is motivated by success.</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/wais.jpg">
        <h2>Wais Khedri</h2>
        <p>He just likes to gym, nothing more.</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/mike.jpg">
          <%--<img class="img-circle" src="/assets/images/developers/mike2.png">--%>
        <h2>Mike Pham</h2>
        <p>He is an alcorexic.</p>
      </div>
    </div><!-- /.span4 -->
  </div><!-- /.row -->
  <!-- Three columns of text below the carousel -->
  <div class="row">
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/bobby.jpg">
        <h2>Bobby Yit</h2>
        <p>He loves long walk on the beach.</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/charles.jpg">
        <h2>Charles Yang</h2>
        <p>His favourite colour is orange.</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/saud.jpg">
        <h2>Saud Musafer</h2>
        <p>He is brown.</p>
      </div>
    </div><!-- /.span4 -->
  </div><!-- /.row -->
  <!-- Three columns of text below the carousel -->
  <div class="row">
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/quang.jpg">
        <h2>Quang Tran</h2>
        <p>He loves his BMW 335i more than his girlfriend.</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/harry.jpg">
        <h2>Harry Haiyaya</h2>
        <p>He has an large Panda with a hole in its bumbum.</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/joe.jpg">
        <h2>Joe Zhou</h2>
        <p>He's from China.</p>
      </div>
    </div><!-- /.span4 -->
  </div><!-- /.row -->
  <!-- Three columns of text below the carousel -->
  <div class="row">
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/developers/robson.jpg">
        <h2>Robson Razafindramary</h2>
        <p>He loves watching Madagascar</p>
      </div>
    </div><!-- /.span4 -->
    <div class="span4">
      <div class="well">
        <img class="img-circle" src="/assets/images/download.png">
        <h2>Sulong Ly</h2>
        <p>He loves japan.</p>
      </div>
    </div><!-- /.span4 --><div class="span4">
    <div class="well">
      <img class="img-circle" src="/assets/images/developers/michelle.jpg">
      <h2>Michelle Hui</h2>
      <p>Peter's girlfriend.</p>
    </div>
  </div><!-- /.span4 -->
  </div><!-- /.row -->

  <!-- /END THE FEATURETTES -->

  <!-- FOOTER -->
  <footer>
    <p class="pull-right"><a href="#">Back to top</a></p>
    <p>&copy; 2013 PeerPen, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
  </footer>

  <p id="back-top">
    <a href="#top"><span></span>Back to Top</a>
  </p>
</div><!-- /.container -->

<script>
  $(document).ready(function(){
    // hide #back-top first
    $("#back-top").hide();

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