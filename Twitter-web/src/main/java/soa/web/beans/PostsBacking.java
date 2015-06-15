package soa.web.beans;

import soa.ejb.local.PostsManager;
import soa.model.entity.PostEntity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import java.util.List;

@Named("postsBacking")
@RequestScoped
public class PostsBacking {
    @Resource(mappedName = "java:jboss/exported/jms/queue/twitter")
    private Queue twitterQueue;
    @Inject
    private JMSContext jmsContext;
    @Inject
    PostsManager postsManager;
    @Inject
    private UserBacking userBacking;
    private String newPostContent;
    private List<PostEntity> posts;

    @PostConstruct
    public void init() {
        posts = postsManager.getAllPosts();
    }

    public String createPost() {
        MapMessage msg = jmsContext.createMapMessage();
        try {
            msg.setString("content", newPostContent);
            msg.setString("author", userBacking.getLoggedUser().getUsername());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        jmsContext.createProducer().send(twitterQueue, msg);
//        postsManager.savePost(newPostContent, userBacking.getLoggedUser());
        return "timeline?faces-redirect=true";
    }

    public PostEntity getSinglePost(int id)
    {
        for(PostEntity p : posts)
        {
            if(p.getPostId() == id) return p;
        }
        return null;
    }


    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public String getNewPostContent() {
        return newPostContent;
    }

    public void setNewPostContent(String newPostContent) {
        this.newPostContent = newPostContent;
    }
}
