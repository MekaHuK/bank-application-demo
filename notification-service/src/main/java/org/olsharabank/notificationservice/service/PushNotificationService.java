package org.olsharabank.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {
    public void sendPushNotification(String msg){
        System.out.println(msg);
    }
}
