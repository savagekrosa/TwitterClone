package soa.web.beans;

import soa.ejb.local.PostsManager;
import soa.model.entity.PostEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name="postsBacking")
@RequestScoped
public class PostsBacking {
    @EJB
    PostsManager postsManager;

    private List<PostEntity> posts;

    @PostConstruct
    public void init() {
        posts = postsManager.getAllPosts();
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }
}