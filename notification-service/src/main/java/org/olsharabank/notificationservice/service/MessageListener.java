package org.olsharabank.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.olsharabank.notificationservice.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageListener(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "your_topic", groupId = "org.olsharabank")
    public void listen(String message) {
        try {
            Message msg = objectMapper.readValue(message, Message.class);
            notificationService.processingNotification(msg);
            System.out.println("Получено: " + msg.amount() + " денег от " +
                    " пользователя с ID: " + msg.sender());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
