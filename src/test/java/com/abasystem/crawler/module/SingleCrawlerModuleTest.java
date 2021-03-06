package com.abasystem.crawler.module;

import com.abasystem.crawler.scheduler.WithSearchAllScheduler;
import com.abasystem.crawler.scheduler.WithoutSearchSingleScheduler;
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
    private WithSearchAllScheduler searchAllScheduler;

    @Autowired
    private WithoutSearchSingleScheduler nonSearchScheduler;

    @Test
    @Transactional
    public void peterPanCrawler() throws Exception {
        searchAllScheduler.jinjuAreaCrawler();
    }

    @Test
    @Transactional
    public void momCrawler() throws Exception {
        nonSearchScheduler.jinjuMomCrawler();
    }

    @Test
    @Transactional
    public void jinjuNCrawler() throws Exception {
        nonSearchScheduler.jinjuNCrawler();
    }

    @Test
    @Transactional
    public void customCrawler() throws Exception {
        nonSearchScheduler.peterOneRoomGangnamSeocho();
    }

//    @Test
//    @Transactional
//    public void daehakDongOneRoomCrawler() throws Exception {
//        searchAllScheduler.daehakDongOneRoomCrawler();
//    }
}
