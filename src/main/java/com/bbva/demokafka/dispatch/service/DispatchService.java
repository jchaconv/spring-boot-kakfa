package com.bbva.demokafka.dispatch.service;

import com.bbva.demokafka.dispatch.message.DispatchPreparing;
import com.bbva.demokafka.dispatch.message.OrderCreated;
import com.bbva.demokafka.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchService {

    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";
    public static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void processJsonPayload(OrderCreated orderCreated) throws Exception {

        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, dispatchPreparing).get();


        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        //get() makes it synchronous, but it can be async
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
        log.info("Order dispatched: {}", orderDispatched);
    }

    public void processStringPayload(String payload) {
        //no op
    }

}
