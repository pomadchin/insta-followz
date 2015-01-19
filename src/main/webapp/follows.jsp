<%@ page import="com.dc.insta.Constants" %>
<%@ page import="org.jinstagram.Instagram" %>
<%@ page import="org.jinstagram.entity.users.feed.UserFeedData" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dc.insta.FollowersList" %>

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
    List<UserFeedData> usersList = followersList.getUserFollows();

%>

<%
    for (UserFeedData userFeedData : usersList) {
%>

<%=userFeedData.getUserName() + " "%>

<%
    }
%>
