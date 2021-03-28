Alibaba RSocket Broker Testcontainers module
============================================

# Quickstart

* Add dependency in the pom.xml

```xml
<dependency>
    <groupId>com.alibaba.rsocket</groupId>
    <artifactId>testcontainers-rsocket-broker-module</artifactId>
    <version>1.0.1</version>
</dependency>
```

* Add static RSocketBrokerContainer instance in test class as following:

```java
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
```

* If your app is Spring Boot, and you can check [Embedded RSocket Broker](https://github.com/alibaba-rsocket-broker/embedded-rsocket-broker)

# References

* Testcontainers: https://www.testcontainers.org/