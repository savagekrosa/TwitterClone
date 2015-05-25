package soa.web.beans;

import soa.ejb.local.PostsManager;
import soa.model.entity.PostEntity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import java.util.List;

@ManagedBean(name = "postsBacking")
@RequestScoped
public class PostsBacking {
    @Resource(mappedName = "java:jboss/exported/jms/queue/twitter")
    private Queue twitterQueue;
    @Inject
    private JMSContext jmsContext;
    @Inject
    PostsManager postsManager;
    @ManagedProperty(value = "#{userBacking}")
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

    public UserBacking getUserBacking() {
        return userBacking;
    }

    public void setUserBacking(UserBacking userBacking) {
        this.userBacking = userBacking;
    }
}
