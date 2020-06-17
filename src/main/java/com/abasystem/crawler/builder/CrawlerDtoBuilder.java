package com.abasystem.crawler.builder;

import com.abasystem.crawler.model.Dto.CrawlerDto;
import com.abasystem.crawler.service.Operator.ParseTemplate;
import com.abasystem.crawler.strategy.ObtainDocumentStrategy;
import com.abasystem.crawler.strategy.ObtainHtmlResourceStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlerDtoBuilder implements Buildable {
    private String id;
    private String password;
    private String fileName;
    private int pageCount;
    private ParseTemplate parseTemplate;
    private ObtainDocumentStrategy documentStrategy;
    private String directory;
    private ObtainHtmlResourceStrategy resourceStrategy;

    public CrawlerDtoBuilder(String id, String password, ParseTemplate parseTemplate, String directory) {
        this.id = id;
        this.password = password;
        this.parseTemplate = parseTemplate;
        this.directory = directory;
    }

    public CrawlerDtoBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CrawlerDtoBuilder password(String password) {
        this.password = password;
        return this;
    }

    public CrawlerDtoBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public CrawlerDtoBuilder pageCount(int maxPage) {
        this.pageCount = maxPage;
        return this;
    }

    public CrawlerDtoBuilder parseTemplate(ParseTemplate parseTemplate) {
        this.parseTemplate = parseTemplate;
        return this;
    }

    public CrawlerDtoBuilder documentStrategy(ObtainDocumentStrategy strategy) {
        this.documentStrategy = strategy;
        return this;
    }

    public CrawlerDtoBuilder directory(String directory) {
        this.directory = directory;
        return this;
    }

    public CrawlerDtoBuilder resourceStrategy(ObtainHtmlResourceStrategy strategy) {
        this.resourceStrategy = strategy;
        return this;
    }

    @Override
    public CrawlerDto build() {
        return new CrawlerDto(this);
    }
}