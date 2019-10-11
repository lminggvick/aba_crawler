package com.abasystem.crawler.Service;

import com.abasystem.crawler.Factory.ServiceFactory;
import com.abasystem.crawler.Mapper.ModelMapper;
import com.abasystem.crawler.Service.Converter.ModelConverter;
import com.abasystem.crawler.Service.Writer.CustomOpenCsv;
import com.abasystem.crawler.Storage.Naver;
import com.abasystem.crawler.Strategy.CsvWriteStrategy;
import com.abasystem.crawler.Strategy.ValidationStrategy;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Init 메소드는 Util 성에 성격이 가까워보인다.
 */
@Service
public class CrawlerService<P extends ModelMapper> extends CustomOpenCsv {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerService.class);

    private static final String[] TABLE_ROW = {
            "번호", "제목", "링크", "설명", "등록일", "주소", "타입", "연락처", "가격", "관리비", "옵션", "이사가능일",
            "방개수", "해당층/전체층", "관리비항목", "난방방식"
    };

    @Autowired
    public ValidationStrategy validationStrategy;

    @Autowired
    private ServiceFactory factory;

    private Document document;
    private List<ModelMapper> properties;
    private String url;
    private String title;

    public CrawlerService() throws Exception {
        super();
    }

    public boolean writeAll(List<P> properties) {
        cw.writeNext(TABLE_ROW);

        int index = 1;

        for(P property : properties) {
            CsvWriteStrategy csvWriteStrategy = factory.writerCreator(property.getClass());
            JsonObject object = ModelConverter.convertModelToJsonObject(property);
            csvWriteStrategy.doWrite(object, cw, index);
            index++;
        }

        if(cw.checkError() == true) {
            return false;
        }

        return true;
    }

    public List<P> parseAll(Elements elements, Map<String, String> cookies) throws IOException {
        properties = new ArrayList<>();

        for (Element post : elements) {
            url = Naver.CAFE_PREFIX.concat(post.select("a").attr("href"));
            title = post.select("a").text();

            document = Jsoup.connect(url)
                    .cookies(cookies)
                    .get();

            Elements els = document.select(".inbox");

            if(validationStrategy.isInvalidPost(els)) {
                continue;
            }

            if ( ! validationStrategy.isPropertyPost(els)) {
                continue;
            }

            boolean flag = validationStrategy.isRegularPost(document.select("#tbody"));

            properties.add(factory.parserCreator(flag).parse(document, url, title));
        }

        return (List<P>) properties;
    }
}