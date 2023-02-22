package de.tab.json;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatContainerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Value("${swagger.port}")
    private int swaggerPort;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        Connector swaggerConnector = new Connector();
        swaggerConnector.setPort(swaggerPort);
        factory.addAdditionalTomcatConnectors(swaggerConnector);
    }
}
