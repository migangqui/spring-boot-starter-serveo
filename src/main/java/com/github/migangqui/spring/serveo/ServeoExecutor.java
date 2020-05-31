package com.github.migangqui.spring.serveo;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ConditionalOnProperty(name = "serveo.enabled", havingValue = "true")
public class ServeoExecutor {

    private final Logger log = LoggerFactory.getLogger(ServeoExecutor.class);

    private final Environment env;

    public ServeoExecutor(final Environment env) {
        this.env = env;
    }

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Value("${spring.application.name:}")
    private String appName;

    @Value("${serveo.custom.domain:}")
    private String configuredCustomDomain;

    @EventListener(ApplicationStartedEvent.class)
    public void run() throws IOException {
        String customDomain;

        if (configuredCustomDomain.isEmpty()) {
            if (appName.isEmpty()) {
                customDomain = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
            } else {
                customDomain = appName;
            }
        } else {
            customDomain = configuredCustomDomain;
        }

        if (validCustomDomain(customDomain)) {
            final String domain = customDomain + ".serveo.net";

            Runtime.getRuntime().exec(String.format("ssh -R %s:80:localhost:%s serveo.net", domain, serverPort));

            log.info("Remote access to application with url -> {}", "https://" + domain + contextPath);
        }
    }

    private boolean validCustomDomain(final String customDomain) {
        boolean valid = true;
        if (customDomain.contains(".") || customDomain.contains("$")) {
            log.warn("Serveo not working: configured custom domain contains invalid characters");
            valid = false;
        }
        return valid;
    }
}
