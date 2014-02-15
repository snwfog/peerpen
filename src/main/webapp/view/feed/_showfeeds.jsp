<%@ page import="java.util.List" %>
<%@ page import="com.peerpen.model.*" %>

<div class="span4">
    <% for(Feedable f : (List<Feedable>)request.getAttribute("feedableList")){ %>
        <% if (f.getType().equalsIgnoreCase("Changeset")){%>

            <div class="row-fluid">
                <div class="card-white">
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
                        <%Integer documentId = ((Changeset)f.getTrueSelf()).getDocumentId();%>
                        <p><a class="btn" href="/peer/<%=sessionUser.getId()%>/document/<%=documentId%>">View Document &raquo;</a></p>
                    </div>
                </div>
            </div>

        <% } %>



        <% if (f.getType().equalsIgnoreCase("Comment")){%>
            <div class="row-fluid">
                <div class="card-white">
                    <div class="card-heading image">
                        <img src="<%= ((Comment)f.getTrueSelf()).getPeer().getAvatar().getDefaultAvatarSource( request ) %>" alt=""/>
                        <div class="card-heading-header">
                            <h4><%=((Comment)f.getTrueSelf()).getPeer().getFirstName()%> commented ...</h4>
                            <span><%=((Comment)f.getTrueSelf()).getMessage()%></span>
                        </div>
                        <div> &nbsp</div>
                    </div>
                </div>
            </div>

        <% } %>

        <% if (f.getType().equalsIgnoreCase("Broadcast")){%>
            <div class="row-fluid">
                    <div class="card-white">
                    <div class="card-heading image">
                        <img src="<%= ((Broadcast)f.getTrueSelf()).getPeer().getAvatar().getServletContextAvatarPath( request ) %>" alt=""/>
                        <div class="card-heading-header">
                            <h4><%=((Broadcast)f.getTrueSelf()).getPeer().getFirstName()%> broadcasted ...</h4>
                            <span><%=((Broadcast)f.getTrueSelf()).getMessage()%></span>
                        </div>
                        <div> &nbsp</div>
                    </div>
                </div>
            </div>

        <% } %>


        <% if (f.getType().equalsIgnoreCase("Joingroup")){%>
        <div class="row-fluid">
            <div class="card-white">
                <div class="card-heading image">
                    <img src="<%= ((Joingroup)f.getTrueSelf()).getPeer().getAvatar().getServletContextAvatarPath( request ) %>" alt=""/>
                    <div class="card-heading-header">
                        <h4><%=((Joingroup)f.getTrueSelf()).getPeer().getFirstName()%> Request to join your group ...</h4>
                        <span>Peer <%=((Joingroup)f.getTrueSelf()).getPeer().getUserName()%> wants to join your <%=((Joingroup)f.getTrueSelf()).getGroup().getGroupName()%> group</span>
                    </div>
                    <div> &nbsp</div>
                </div>
            </div>
        </div>

        <% } %>

    <% } %>

</div>