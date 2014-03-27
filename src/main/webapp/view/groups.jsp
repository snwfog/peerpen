<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<%
    List<Group> groups = (List<Group>) request.getAttribute("groups");
    String sort = (String) request.getAttribute("sort");
%>

<div class="wrap-container container">
    <div class="row row-offcanvas row-offcanvas-right">
        <h1 class="groups-header">Join a group!</h1>
        <div >
            <div class="row">

                <div class="groups-sort-body">
                    <p class="groups-sort-text">sort by:</p>
                    <form action="/group" method="post">
                        <select name="sort" onchange="this.form.submit()">
                            <option value="az" <%= (sort.contentEquals("az") ? "selected=\"selected\"" : "")%>>A-Z</option>
                            <option value="za" <%= (sort.contentEquals("za") ? "selected=\"selected\"" : "")%>>Z-A</option>
                            <option value="fc" <%= (sort.contentEquals("fc") ? "selected=\"selected\"" : "")%>>Newest</option>
                            <option value="lc" <%= (sort.contentEquals("lc") ? "selected=\"selected\"" : "")%>>Oldest</option>
                            <option value="mp" <%= (sort.contentEquals("mp") ? "selected=\"selected\"" : "")%>>Most popular</option>
                            <option value="lp" <%= (sort.contentEquals("lp") ? "selected=\"selected\"" : "")%>>Least popular</option>
                            <option value="pd" <%= (sort.contentEquals("pd") ? "selected=\"selected\"" : "")%>>Pending</option>
                        </select>
                        <input type="hidden" name="_method" value="put" />
                    </form>
                </div>

                <%
                    for (Group group : groups){
                %>
                <div class="col-4 col-sm-3 col-lg-4" >
                    <div class="groups-box-body">
                        <h2 class="groups-box-header"><a href="group/<%= group.getId()%>" class="groups-box-header-link"><%= group.getGroupName()%></a></h2>
                        <p class="groups-size-body">
                            <span class="groups-size-text">&nbsp;&nbsp;<span style="font-size: medium;"><i class="fa fa-users"></i>&nbsp;<span class="joined-<%=group.getId()%>"><%= group.getPeers().size()%></span></span></span>
                                <%= group.getShortDescription()%>
                        <p>
                        <div class="group-<%= group.getId()%> groups-content" >

                            <% if(group.getIsJoined(sessionUser.getId())){ %>
                            <form action="" class="form-horizontal" id="<%= group.getId()%>" role="form">
                                <input type="hidden" name="groupid" value="<%= group.getId()%>">
                                <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>">
                                <input type="hidden" name="_method" value="delete"/>
                                <% if(group.getAdminId()!=sessionUser.getId()){%>
                                <button type="button" class="btn btn-danger" onclick="leave(<%= group.getId()%>);">Leave Group!</button>
                                <%}else
                                {%>
                                <button type="button" class="btn btn-default">My Group!</button>
                                <%}%>
                            </form>
                            <% }else if(group.getPending(sessionUser.getId())){%>
                            <form action="" class="form-horizontal" id="<%= group.getId()%>" role="form">
                                <input type="hidden" name="groupid" value="<%= group.getId()%>">
                                <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>">
                                <input type="hidden" name="_method" value="put"/>
                                <button type="button" class="btn btn-default" onclick="cancel(<%= group.getId()%>);">Pending request</button>
                            </form>
                            <%}else{%>
                            <form action="" class="form-horizontal" id="<%= group.getId()%>" role="form" style="text-align:center;">
                                <input type="hidden" name="groupid" value="<%= group.getId()%>">
                                <input type="hidden" name="peerid" value="<%= sessionUser.getId()%>">
                                <button type="button" class="btn btn-primary" onclick="join(<%= group.getId()%>);">Join!</button>
                            </form>
                            <%}%>
                        </div>
                        </p>
                    </div>
                </div>
                    <%}%>
            </div>


            <div class="groups-new-group-body">
                <h1 class="groups-new-group-text">Your desired group not enlisted? Create your own!</h1>
                <button class="btn btn-warning btn-lg" data-toggle="modal" data-target="#myModal">Create a new group</button>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Create a group</h4>
                </div>
                <form action="/group" method="POST">
                    <div class="modal-body">
                        <input type="text" class="form-control" name="name" placeholder="Group name" />
                        <br />
                        <textarea class="form-control group-description" maxlength="200" name="description" placeholder="Description (200 char. max)" ></textarea>
                        <span class="countdown"></span>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<%@ include file="/view/includes/static/footer.jsp" %>