package com.bbva.demokafka.dispatch.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import static org.mockito.Mockito.*;

class DispatchServiceTest {

    private DispatchService dispatchService;
    private KafkaTemplate kafkaProducerMock;

    @BeforeEach
    void setUp() {
        kafkaProducerMock = mock(KafkaTemplate.class);
        dispatchService = new DispatchService(kafkaProducerMock);
    }

    @Test
    void processStringPayload() {
        dispatchService.processStringPayload("process");
    }
}