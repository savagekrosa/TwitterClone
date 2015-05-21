package soa.web.beans;

import soa.ejb.local.PostsManager;
import soa.model.entity.PostEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name="postsBacking")
@RequestScoped
public class PostsBacking {
    @EJB
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
        postsManager.savePost(newPostContent, userBacking.getLoggedUser());
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
