package com.alibaba.rsocket.testcontainers;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

/**
 * RSocket Broker Container
 *
 * @author leijuan
 */
public class RSocketBrokerContainer extends GenericContainer<RSocketBrokerContainer> {
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("linuxchina/alibaba-rsocket-broker");
    private static final String DEFAULT_TAG = "1.0.1";

    public RSocketBrokerContainer() {
        this(DEFAULT_IMAGE_NAME.withTag(DEFAULT_TAG));
    }

    public RSocketBrokerContainer(final String dockerImageName) {
        this(DockerImageName.parse(dockerImageName));
    }

    public RSocketBrokerContainer(final DockerImageName dockerImageName) {
        super(dockerImageName);
        dockerImageName.assertCompatibleWith(DEFAULT_IMAGE_NAME);
        withExposedPorts(9997, 9998, 9999);
        waitingFor(Wait.forListeningPort());
    }

    public String getRSocketBrokerUri() {
        Integer rsocketMappedPort = getMappedPort(9999);
        return "tcp://localhost:" + rsocketMappedPort;
    }

    public String getWebConsoleUri() {
        Integer webExposedPort = getMappedPort(9998);
        return "http://localhost:" + webExposedPort;
    }
}
