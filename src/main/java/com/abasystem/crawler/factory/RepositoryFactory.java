package com.abasystem.crawler.factory;

import com.abasystem.crawler.mapper.ModelMapper;
import com.abasystem.crawler.model.property.IrregularProperty;
import com.abasystem.crawler.model.property.RegularProperty;
import com.abasystem.crawler.strategy.BasicQueryStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryFactory.class);

    @Autowired
    private BasicQueryStrategy<RegularProperty> regularRepository;

    @Autowired
    private BasicQueryStrategy<IrregularProperty> irregularRepository;

    public <P extends ModelMapper> BasicQueryStrategy<P> getTypeRepositoryCreator(Class clazz) {
        if(clazz.equals(RegularProperty.class)) {
            return (BasicQueryStrategy<P>) regularRepository;
        }
        else {
            return (BasicQueryStrategy<P>) irregularRepository;
        }
    }
}
