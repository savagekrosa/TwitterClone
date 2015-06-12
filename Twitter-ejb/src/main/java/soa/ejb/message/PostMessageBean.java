package soa.ejb.message;

import soa.ejb.local.PostsManager;
import soa.ejb.local.UserManager;
import soa.model.entity.UserEntity;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

@MessageDriven(name = "PostMessageEJB", activationConfig = {
        @ActivationConfigProperty( propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty( propertyName = "destination",
                propertyValue ="/queue/twitterPosts")
})
public class PostMessageBean implements javax.jms.MessageListener {
    @EJB
    private PostsManager postsManager;
    @EJB
    private UserManager userManager;
    @Resource
    private MessageDrivenContext mdctx;
    @Override
    public void onMessage(Message msg) {
        MapMessage message = (MapMessage) msg;
        try {
            String content = message.getString("content");
            String authorUsername =  message.getString("author");
            UserEntity author = userManager.findByUsername(authorUsername);
            postsManager.savePost(content, author);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
