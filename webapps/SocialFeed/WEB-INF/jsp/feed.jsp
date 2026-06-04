<%@ page import="java.util.List" %>
<%@ page import="feed.data.Post" %>
<!DOCTYPE html>
<html>
<head>
    <title>Social Feed Web App</title>

    <script src="/SocialFeed/js/jquery-4.0.0.min.js"></script>
    <script src="/SocialFeed/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/SocialFeed/css/bootstrap.css">
</head>
<body>

<div class="container">
    <nav class="navbar navbar-default">
        <ul class="nav navbar-nav">
            <li><a href="">Social Feed Web App</a></li>
        </ul>
    </nav>

    <h1><%= request.getAttribute("title") %></h1>

    <%
        List<Post> posts = (List<Post>)request.getAttribute("posts");
        if(posts == null){
    %>
    <p>This user has no posts.</p>
    <%
    }
    else{
        for(Post post : posts){
    %>
    <div class="panel panel-default">
        <div class="panel-heading"><h4><a href="/SocialFeed/feed/<%= post.getUser() %>"><%= post.getUser() %></a></h4></div>
        <div class="panel-body"><%= post.getMessage() %></div>
        <div class="panel-footer">at <%= post.getDate().toString() %></div>

    </div>
    <%
            }
        }
    %>
</div>

</body>
</html>