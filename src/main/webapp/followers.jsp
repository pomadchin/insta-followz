<jsp:include page="common/header.jsp"/>
<%@ page import="com.dc.insta.Constants" %>
<%@ page import="org.jinstagram.Instagram" %>

<%

    Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);

    Instagram instagram = null;

    if (objInstagram != null) {
        instagram = (Instagram) objInstagram;
    } else {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

%>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">jInstagram</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="profile.jsp">Profile</a></li>
                <li class="active"><a href="followers.jsp">Followers</a></li>
                <li><a href="gallery.jsp">Gallery</a></li>
                <li><a href="popular.jsp">Popular</a></li>
                <li><a href="search.jsp">Search</a></li>
                <li><a href="logout.jsp">Logout</a></li>

            </ul>
        </div>
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-12">
            <h1 class="page-header">User Profile</h1>
        </div>
        <%
            /*FollowersList followersList = new FollowersList(instagram);
            Triple<List<UserFeedData>, List<UserFeedData>, List<UserFeedData>> userListTriple =
                    followersList.getFollowers();*/

        %>

        <p class="lead">
            <p>Follows : <a href="follows.jsp">link <%--(<%=userListTriple.getSnd().size()%>)--%></a></p>
            <p>Followed By : <a href="followedBy.jsp">link <%--(<%=userListTriple.getFst().size()%>)--%></a></p>
            <p>Followed Back : <a href="followedBack.jsp">link <%--(<%=userListTriple.getTrd().size()%>)--%></a></p>
        </p>

    </div>

    <hr>

<jsp:include page="common/footer.jsp"/>