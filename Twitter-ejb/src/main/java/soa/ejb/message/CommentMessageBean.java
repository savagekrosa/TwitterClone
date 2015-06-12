package soa.ejb.message;

import soa.ejb.local.CommentsManager;
import soa.ejb.local.PostsManager;
import soa.ejb.local.UserManager;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;

/**
 * Created by krzysiek on 6/12/15.
 */
@MessageDriven(name = "PostMessageEJB", activationConfig = {
        @ActivationConfigProperty( propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty( propertyName = "destination",
                propertyValue ="/queue/twitterPosts")
})
public class CommentMessageBean {
    @EJB
    CommentsManager commentsManager;
    @EJB
    UserManager userManager;
    @Resource
    private MessageDrivenContext mdctx;

}
