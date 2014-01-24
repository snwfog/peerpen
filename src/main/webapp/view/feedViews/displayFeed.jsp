<%@ page import="com.peerpen.model.Feedable" %>
<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.Changeset" %>
<%@ page import="com.peerpen.model.Comment" %>

<% for(Feedable f : (List<Feedable>)request.getAttribute("FeedableList")){ %>
    <% if (f.getType().equalsIgnoreCase("Changeset")){%>
    <div class="row-fluid">
        <div class="card">
            <h2 class="card-heading simple">
                <b><%=((Changeset)f.getTrueSelf()).getPeer().getFirstName()%></b>
                suggested changes to
                <b><%=((Changeset)f.getTrueSelf()).getDocument().getDocName()%></b>
            </h2>
            <div class="card-body">
                <div class="span6">

                    <div class="bs-example">
                        <div class="bs-example-label">From</div>
                        <p style="word-wrap: break-word;">
                          <%=((Changeset)f.getTrueSelf()).getContent()%>
                        </p>
                    </div>

                </div>

                <div class="span6">
                    <div class="bs-example">
                        <div class="bs-example-label">To</div>
                        <%=((Changeset)f.getTrueSelf()).getHunk().getContent()%>
                    </div>
                </div>

                <div class="clearfix"></div>
                <p><a class="btn" href="#">View Document &raquo;</a></p>
            </div>
        </div>
    </div>

    <% } %>



    <% if (f.getType().equalsIgnoreCase("Comment")){%>
    <div class="row-fluid">
        <div class="card">
            <div class="card-heading image">
                <img src="holder.js/46x46" alt=""/>
                <div class="card-heading-header">
                    <h4><%=((Comment)f.getTrueSelf()).getPeer().getFirstName()%> commented ...</h4>
                    <span><%=((Comment)f.getTrueSelf()).getMessage()%></span>
                </div>
                <div> &nbsp</div>
            </div>
        </div>
    </div>

    <% } %>

<% } %>