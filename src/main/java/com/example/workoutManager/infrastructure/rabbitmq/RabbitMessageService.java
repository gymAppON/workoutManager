package com.example.workoutManager.infrastructure.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RabbitMessageService {

    private final RabbitTemplate template;

    @Value("${rabbitmq.queue.name}")
    private String messageQueue;

    @Value("${rabbitmq.queue_with_delay.name}")
    private String messageWithDelayQueue;

    /**
     * Отправляет сообщение в очередь
     * @param message Сообщение
     */
    public void sendMessage(String message) {
        template.convertAndSend(messageQueue, message);
    }

    /**
     * Посылает сообщение в очередь, которая обрабатывается слушателем с некоторой задержкой
     * @param message Сообщение
     */
    public void sendMessageToQueueWithDelay(String message) {
        template.convertAndSend(messageWithDelayQueue, message);
    }

}
