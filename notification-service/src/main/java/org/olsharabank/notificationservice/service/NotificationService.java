package org.olsharabank.notificationservice.service;

import lombok.AllArgsConstructor;
import org.olsharabank.notificationservice.dto.Message;
import org.olsharabank.notificationservice.entity.Notification;
import org.olsharabank.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final PushNotificationService pushNotificationService;


    @Transactional
    public void processingNotification(Message msg){
        Notification notification = new Notification();
        String info = "User with UUID: " + msg.sender() + " sent to you " + msg.amount() + " money";
        notification.setMessage(info);
        notification.setTimestamp(System.currentTimeMillis());

        notificationRepository.save(notification);

        pushNotificationService.sendPushNotification(info);
    }
}
