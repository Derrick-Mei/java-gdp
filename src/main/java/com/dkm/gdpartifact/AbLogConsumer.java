package com.dkm.gdpartifact;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
// tells spring to run this constantly in background
@Service
public class AbLogConsumer
{
    @RabbitListener(queues = AaGdpNameApplication.QUEUE_NAME)
    public void consumeMessage(final Message cm)
    {
        log.info("\n\nReceived Message from Ab: {}\n\n", cm.toString());
    }
}
