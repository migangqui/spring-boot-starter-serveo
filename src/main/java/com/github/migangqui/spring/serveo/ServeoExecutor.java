package com.github.migangqui.spring.serveo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "serveo.enabled", havingValue = "true")
public class ServeoExecutor {

    private final Environment env;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${spring.application.name}")
    private String appName;

    @EventListener(ApplicationStartedEvent.class)
    public void run() throws IOException {
        String customDomain = env.getProperty("serveo.custom.domain");

        if (customDomain == null) {
            if (appName == null) {
                customDomain = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
            } else {
                customDomain = appName;
            }
        } else {
            validateCustomDomain(customDomain);
        }

        final String domain = customDomain + ".serveo.net";

        Runtime.getRuntime().exec(String.format("ssh -R %s:80:localhost:%s serveo.net", domain, serverPort));

        log.info("Remote access to application with url -> {}", "https://" + domain + contextPath);
    }

    private void validateCustomDomain(final String customDomain) {
        if (customDomain.contains(".") || customDomain.contains("$")) {
            throw  new IllegalArgumentException("Custom domain contains invalid characters");
        }
    }
}
