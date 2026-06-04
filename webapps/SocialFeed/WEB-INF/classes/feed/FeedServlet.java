package feed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import feed.data.Post;

public class FeedServlet extends HttpServlet {

    /**
     * All of the posts, ordered by time. Old messages at the
     * beginning, new messages at the end.
     */
    private List<Post> postsByTime = new ArrayList<>();

    /**
     * Map of user names to posts made by that user.
     */
    private Map<String, List<Post>> postsByUser = new HashMap<>();

    @Override
    public void init(){

        long now = System.currentTimeMillis();
        // for fake data, add an offset so posts aren't all at the same time
        long offset = 15*60*1000;

        // add a bunch of fake data for testing
        addPost("Ada", "Hello world!", now+offset);
        addPost("Grace", "I've always been more interested in the future than in the past.", now+offset*2);
        addPost("Stanley", "Meow", now+offset*3);
        addPost("Stanley", "Purr", now+offset*4);
        addPost("Grace", "From then on, when anything went wrong with a computer, we said it had bugs in it.", now+offset*5);
        addPost("Ada", "Mathematical Science shows what is. It is the language of the unseen relations between things.", now+offset*6);
        addPost("Grace", "Please cut off a nanosecond and send it over to me.", now+offset*7);
        addPost("Stanley", "Growl!", now+offset*8);
    }

    /**
     * Adds a post to the postsByTime and postsByUser data structures.
     */
    private void addPost(String user, String message, long time){
        Post post = new Post(user, message, new Date(time));
        postsByTime.add(post);

        if(!postsByUser.containsKey(user)){
            postsByUser.put(user, new ArrayList<>());
        }
        postsByUser.get(user).add(post);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String user = requestUrl.substring("/SocialFeed/feed/".length());
        System.out.println("User : " +  user);

        if("".equals(user)){
            request.setAttribute("title", "All Posts");
            request.setAttribute("posts", postsByTime);
        }
        else{
            request.setAttribute("title", "Posts by " + user);

            if(postsByUser.containsKey(user)){
                request.setAttribute("posts", postsByUser.get(user));
            }
        }

        request.getRequestDispatcher("/WEB-INF/jsp/feed.jsp").forward(request,response);
    }
}