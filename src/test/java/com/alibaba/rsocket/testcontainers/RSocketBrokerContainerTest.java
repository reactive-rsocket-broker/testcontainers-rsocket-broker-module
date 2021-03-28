package com.alibaba.rsocket.testcontainers;


import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.HttpURLConnection;
import java.net.URL;

@Testcontainers
public class RSocketBrokerContainerTest {
    @Container
    static RSocketBrokerContainer rsocketBrokerContainer = new RSocketBrokerContainer();

    @Test
    public void testBrokerIsReady() throws Exception {
        System.out.println("RSocket Server URI: " + rsocketBrokerContainer.getRSocketBrokerUri());
        System.out.println("RSocket WebConsole URI: " + rsocketBrokerContainer.getWebConsoleURL());
        URL healthURL = new URL(rsocketBrokerContainer.getActuatorURL() + "/actuator/health");
        HttpURLConnection urlConnection = (HttpURLConnection) healthURL.openConnection();
        int code = urlConnection.getResponseCode();
        System.out.println("Health check response code: " + code);
    }
}
