package com.abasystem.crawler.unit;

import com.abasystem.crawler.Storage.Naver;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class LoginTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    private WebClient webClient;
    private HtmlPage htmlPage;

    @Before
    public void setup() {
        this.webClient = new WebClient();

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
    }

    @After
    public void after() {
        webClient.close();
    }

    @Test
    public void doLogin() throws IOException {
        htmlPage = webClient.getPage(Naver.LOGIN_URL);

        HtmlTextInput inputId = htmlPage.getFirstByXPath(Naver.ID_XPATH);
        HtmlPasswordInput inputPassword = htmlPage.getFirstByXPath(Naver.PW_XPATH);
        HtmlSubmitInput inputSubmit = htmlPage.getFirstByXPath(Naver.SUBMIT_XPATH);

        inputId.setText(Naver.ID);
        inputPassword.setText(Naver.PASSWORD);
        inputSubmit.dblClick();

        assertTrue(htmlPage.asText().contains("Naver Sign in"));
    }

    @Test
    public void getCookie() throws IOException {
        doLogin();
        Set<Cookie> cookies = this.webClient.getCookieManager().getCookies();
        logger.debug("Cookie : {}", cookies);
        assertNotEquals(cookies.size(), 0);
    }
}