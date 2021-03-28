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

    /**
     * construct RSocket broker container from "linuxchina/alibaba-rsocket-broker:1.0.1"
     */
    public RSocketBrokerContainer() {
        this(DEFAULT_IMAGE_NAME.withTag(DEFAULT_TAG));
    }

    /**
     * construct RSocket broker container from image name
     *
     * @param dockerImageName docker image name, such as "linuxchina/alibaba-rsocket-broker:1.0.0"
     */
    public RSocketBrokerContainer(final String dockerImageName) {
        this(DockerImageName.parse(dockerImageName));
    }

    /**
     * construct RSocket broker container from DockerImageName object
     *
     * @param dockerImageName DockerImageName object
     */
    public RSocketBrokerContainer(final DockerImageName dockerImageName) {
        super(dockerImageName);
        dockerImageName.assertCompatibleWith(DEFAULT_IMAGE_NAME);
        withExposedPorts(9997, 9998, 9999);
        waitingFor(Wait.forListeningPort());
    }

    /**
     * get RSocket broker connection uri
     *
     * @return connection uri, format as "tcp://localhost:xxxx"
     */
    public String getRSocketBrokerUri() {
        Integer rsocketMappedPort = getMappedPort(9999);
        return "tcp://localhost:" + rsocketMappedPort;
    }

    /**
     * get RSocket broker web console URL
     *
     * @return connection uri, format as "http://localhost:xxxx"
     */
    public String getWebConsoleURL() {
        Integer webExposedPort = getMappedPort(9998);
        return "http://localhost:" + webExposedPort;
    }

    /**
     * get RSocket broker Spring Boot actuator URL
     *
     * @return connection uri, format as "http://localhost:xxxx"
     */
    public String getActuatorURL() {
        Integer managementExposedPort = getMappedPort(9997);
        return "http://localhost:" + managementExposedPort;
    }
}
