<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Comment" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

               <% Peer peer = (Peer) session.getAttribute("user"); %>
                <div class="row">
                  <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                      <div class="caption">
                        <% for (Comment c : (List<Comment>) request.getAttribute("comments"))
                        {
                        %>
                        <div class="panel panel-default">
                          <div class="panel-heading"><%= c.getName() %><div style="float:right"><a href="#"><span class="glyphicon glyphicon-thumbs-up"></span></a> / <a href="#"><span class="glyphicon glyphicon-thumbs-down"></span></a></div></div>
                          <div class="panel-body">
                            <%= c.getMessage() %>
                          </div>
                        </div>

                        <% } %>
                        <div class="panel panel-default">
                          <div class="panel-heading"><%= peer.getFirstName() %> <%= peer.getLastName() %></div>
                          <div class="panel-body">
                            <form method="POST" action="/comment.do">
                            <textarea name="comment" rows="4" cols="45"  resize="none">I WANT TO GO UP YOUR DIAGON ALLEY</textarea>
                            <br /><br />
                            <%--<input type="hidden" name="thread" value="<>">--%>
                            <button type="submit" class="btn btn-success">Post</button>
                            </form>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
<%@ include file="/view/includes/static/footer.jsp" %>