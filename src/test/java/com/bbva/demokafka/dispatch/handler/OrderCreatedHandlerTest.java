package com.bbva.demokafka.dispatch.handler;

import com.bbva.demokafka.dispatch.message.OrderCreated;
import com.bbva.demokafka.dispatch.service.DispatchService;
import com.bbva.demokafka.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler orderCreatedHandler;
    private DispatchService dispatchService;


    @BeforeEach
    void setUp() {
        dispatchService = mock(DispatchService.class);
        orderCreatedHandler = new OrderCreatedHandler(dispatchService);
    }

    /*
    @Test
    void listenStringPayload() {
        orderCreatedHandler.listenStringPayload("test-payload");
        verify(dispatchService, times(1)).processStringPayload(anyString());
    }
    */

    @Test
    void listenJsonPayload() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        orderCreatedHandler.listenJsonPayload(testEvent);
        verify(dispatchService, times(1)).processJsonPayload(testEvent);
    }

    @Test
    void listenJsonPayload_Error() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());

        doThrow(new RuntimeException("Service failure")).when(dispatchService).processJsonPayload(testEvent);

        orderCreatedHandler.listenJsonPayload(testEvent);
        verify(dispatchService, times(1)).processJsonPayload(testEvent);
    }





}