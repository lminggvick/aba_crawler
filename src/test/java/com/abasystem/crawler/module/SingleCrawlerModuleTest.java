package com.abasystem.crawler.module;

import com.abasystem.crawler.Scheduler.Special.HappyHouseCrawlingScheduler;
import com.abasystem.crawler.Scheduler.Special.JinjuMomCrawlingScheduler;
import com.abasystem.crawler.Scheduler.Special.PerterPanCrawlingScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleCrawlerModuleTest {
    private static final Logger logger = LoggerFactory.getLogger(SingleCrawlerModuleTest.class);

    @Autowired
    private JinjuMomCrawlingScheduler momScheduler;

    @Autowired
    private HappyHouseCrawlingScheduler happyScheduler;

    @Autowired
    private PerterPanCrawlingScheduler peterPanScheduler;

    @Test
    @Transactional
    public void peterPanCrawler() {
        peterPanScheduler.jinjuAreaCrawler();
    }

    @Test
    @Transactional
    public void momCrawler() {
        momScheduler.momCrawler();
    }

    @Test
    @Transactional
    public void happyCrawler() {
        happyScheduler.happyHouseCrawler();
    }
}