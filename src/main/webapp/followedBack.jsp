<%@ page import="com.dc.insta.Constants" %>
<%@ page import="org.jinstagram.Instagram" %>
<%@ page import="org.jinstagram.entity.users.feed.UserFeedData" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dc.insta.FollowersList" %>
<%@ page import="com.dc.insta.core.Triple" %>

<%

    Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);

    Instagram instagram = null;

    if (objInstagram != null) {
        instagram = (Instagram) objInstagram;
    } else {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    FollowersList followersList = new FollowersList(instagram);
    com.dc.insta.core.Triple<List<UserFeedData>, List<UserFeedData>, List<UserFeedData>> userListTriple =
            followersList.getFollowers();

%>

<%
    for (UserFeedData userFeedData : userListTriple.getTrd()) {
%>

<%=userFeedData.getUserName() + " "%>

<%
    }
%>

<hr />

Follows: <%= userListTriple.getSnd().size()%>

<br />

Followed By: <%= userListTriple.getFst().size()%>
