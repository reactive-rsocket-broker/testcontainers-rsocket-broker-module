package com.alibaba.rsocket.testcontainers;


import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class RSocketBrokerContainerTest {
    @Container
    static RSocketBrokerContainer rsocketBrokerContainer = new RSocketBrokerContainer();

    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(rsocketBrokerContainer.getRSocketBrokerUri());
        System.out.println(rsocketBrokerContainer.getWebConsoleUri());
    }
}
