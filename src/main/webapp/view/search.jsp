<%@ page import="java.util.ArrayList" %>
<%@ include file="/view/includes/static/header.jsp" %>

<form action="search.do" method="get" align="center">
    Search <input type="text" name="query" />
    <input type="submit" name="submit" value="OK" />
    <br />
    <input type="radio" name="area" value="all" checked />All
    <input type="radio" name="area" value="documents" />Documents
    <input type="radio" name="area" value="peers" />Peers
    <input type="radio" name="area" value="groups" />Groups
    <input type="radio" name="area" value="tags" />Tags
</form>

<!-- Handling search result -->
${searchResults}

<%--<c:forEach items="${searchResults}" var="searchResult" begin="0" end="4">--%>
<%--<p>${searchResult}</p>--%>
<%--</c:forEach>--%>


<%@ include file="/view/includes/static/footer.jsp" %>