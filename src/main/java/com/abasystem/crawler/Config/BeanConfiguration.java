package com.abasystem.crawler.Config;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(BeanConfiguration.class);

    @Bean
    public Map<String, String> hashMap() {
        return new HashMap<>();
    }

    @Bean
    @Qualifier("webclient")
    public WebClient getWebClient() {
        WebClient webClient = new WebClient();

        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        logger.info("==== webclient init!!");

        return webClient;
    }

    @Bean
    public CookieManager getCookieManager() {
        return new CookieManager();
    }
}
