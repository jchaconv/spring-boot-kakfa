package com.bbva.demokafka.dispatch.handler;

import com.bbva.demokafka.dispatch.message.OrderCreated;
import com.bbva.demokafka.dispatch.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    //  === String payload ===
    /*@KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer"
    )
    public void listen(String payload) {
        log.info("Received message :: payload : " + payload);
        dispatchService.process(payload);
    }*/


    //JSON Payload
    @KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer"
    )
    public void listen(OrderCreated payload) {
        log.info("Received message :: payload : " + payload);
        dispatchService.process(payload);
    }


}
