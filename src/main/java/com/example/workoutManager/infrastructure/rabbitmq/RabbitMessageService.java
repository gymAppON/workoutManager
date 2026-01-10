package com.example.workoutManager.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RabbitMessageService {

    private final RabbitTemplate template;

    @Value("${rabbitmq.queue.name}")
    private String messageQueue;

    @Value("${rabbitmq.user_check_queue.name}")
    private String userQueue;


    public boolean checkUserExistsViaRabbit(UUID userId) {
        Boolean exists = (Boolean) template.convertSendAndReceive(
                "internal-exchange",
                userQueue,
                userId
        );
        return exists != null && exists;
    }

    /**
     * Отправляет сообщение в очередь
     * @param message Сообщение
     */
    public void sendMessage(String message) {
        template.convertAndSend(messageQueue, message);
    }
}
