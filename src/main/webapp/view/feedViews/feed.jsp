<%@ page import="com.peerpen.model.Feedable" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Changeset" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ include file="/view/includes/static/header.jsp" %>

<% Peer peer = (Peer) request.getAttribute("peer"); %>

<body>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="brand" href="#">PeerPen</a>
            </div>
        </div>
    </div>

    <br>



    <div class="container-fluid right">
        <div class="row-fluid">

            <div class="span4">
                <div class="span11">
                    <div class="well">
                        <img class="img-circle" src=<%= peer.getAvatar() !=null ? "" : "http://www.newyorker.com/online/blogs/photobooth/NASAEarth-01.jpg" %>>
                        <h2>User Name</h2>
                        <p><a class="btn btn-primary" href="#">View details &raquo;</a></p>
                    </div>
                </div><!-- /.span4 -->
            </div>

            <div class="row-fluid">
                    <div class="span8">
                       <div class="col-md-2 col-md-offset-5">
                           <div class="row-fluid">
                               <div class="navbar navbar-inverse">
                                   <div class="navbar-inner">
                                       <div class="container-fluid">
                                           <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                               <span class="icon-bar"></span>
                                               <span class="icon-bar"></span>
                                               <span class="icon-bar"></span>
                                           </button>
                                           <a class="brand" href="#">Feeds</a>
                                       </div>
                                   </div>
                               </div>
                           </div

                           <% if(request.getAttribute("FeedableList") != null){%>
                                <%@ include file="/view/feedViews/displayFeed.jsp" %>
                           <% }else{ %>
                                   <div class="row-fluid">
                                   <div class="card">
                                   <h2 class="card-heading simple">You Got No Feeds</h2>
                                   </div>
                                   </div>
                           <%}%>

                                 <%--<div class="row-fluid">--%>
                                    <%--<div class="card">--%>
                                        <%--<h2 class="card-heading simple">Heading</h2>--%>
                                        <%--<div class="card-body">--%>
                                            <%--<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>--%>
                                            <%--<p><a class="btn" href="#">View details &raquo;</a></p>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                 <%--</div>--%>

                                 <%--<div class="row-fluid">--%>
                                        <%--<div class="card">--%>
                                            <%--<div class="card-heading image">--%>
                                                <%--<img src="holder.js/46x46" alt=""/>--%>
                                                <%--<div class="card-heading-header">--%>
                                                    <%--<h3>Simple News Card</h3>--%>
                                                    <%--<span>Published today - 08.34 AM</span>--%>
                                                <%--</div>--%>
                                                <%--<div> &nbsp</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                 <%--</div>--%>


                        </div><!--/row-->
                    </div><!--/span-->
            </div><!--/row-->
        </div>

        <hr>

        <footer>
            <p>&copy; Company 2013</p>
        </footer>
    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/bootstrap-transition.js"></script>
    <script src="../assets/js/bootstrap-alert.js"></script>
    <script src="../assets/js/bootstrap-modal.js"></script>
    <script src="../assets/js/bootstrap-dropdown.js"></script>
    <script src="../assets/js/bootstrap-scrollspy.js"></script>
    <script src="../assets/js/bootstrap-tab.js"></script>
    <script src="../assets/js/bootstrap-tooltip.js"></script>
    <script src="../assets/js/bootstrap-popover.js"></script>
    <script src="../assets/js/bootstrap-button.js"></script>
    <script src="../assets/js/bootstrap-collapse.js"></script>
    <script src="../assets/js/bootstrap-carousel.js"></script>
    <script src="../assets/js/bootstrap-typeahead.js"></script>
</body>

<%@ include file="/view/includes/static/footer.jsp" %>
