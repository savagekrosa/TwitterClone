package soa.rest.services;


import soa.ejb.local.HashtagManager;
import soa.model.entity.PostEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/hashtag")
@Stateless
public class HashtagService {
    @Inject
    HashtagManager hashtagManager;

    @GET
    @Path("/{param}")
    @Produces("application/json")
    public List<PostEntity> printMessage(@PathParam("param") String tag) {
        List<PostEntity> posts = hashtagManager.getPostsTaggedWith(tag);
        return posts;

    }
}
