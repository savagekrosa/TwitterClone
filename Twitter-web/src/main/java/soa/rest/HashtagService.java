package soa.rest;


import soa.ejb.local.HashtagManager;
import soa.model.entity.PostEntity;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/hashtag")
public class HashtagService {
    @EJB
    HashtagManager hashtagManager;

    @GET
    @Path("/{param}")
    @Produces("application/json")
    public List<PostEntity> printMessage(@PathParam("param") String tag) {
        List<PostEntity> posts = hashtagManager.getPostsTaggedWith(tag);
        System.out.println("lkjh" + posts.size());
        return posts;

    }
}
