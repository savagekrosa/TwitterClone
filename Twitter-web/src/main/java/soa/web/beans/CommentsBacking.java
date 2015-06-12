package soa.web.beans;

import soa.ejb.local.PostsManager;
import soa.model.entity.CommentEntity;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import java.util.List;

/**
 * Created by krzysiek on 6/12/15.
 */
@Named("commentBacking")
@RequestScoped
public class CommentsBacking {
    @Resource(mappedName = "java:jboss/exported/jms/queue/twitter")
    @Inject
    private JMSContext jmsContext;
    @Inject
    PostsManager postsManager;
    @Inject
    private UserBacking userBacking;
    private String newCommentContent;
    private List<CommentEntity> comments;

}
