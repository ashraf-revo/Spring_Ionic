package org.revo.Event;

import org.revo.Domain.Post;
import org.revo.Service.SimpMessagingService;
import org.revo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

/**
 * Created by revo on 7/29/16.
 */
@Component
public class PostEvent {
    @Autowired
    SimpMessagingService messagingService;
    @Autowired
    UserService userService;

    @EventListener
    void BeforeSavePost(AfterSaveEvent<Post> event) {
        messagingService.send(userService.followers( event.getSource().getUser()), event.getSource());
    }
}
