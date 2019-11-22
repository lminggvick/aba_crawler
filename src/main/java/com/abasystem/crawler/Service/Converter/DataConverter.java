package com.abasystem.crawler.Service.Converter;

import com.abasystem.crawler.Mapper.ModelMapper;
import com.abasystem.crawler.Service.CustomValidator;
import com.abasystem.crawler.Storage.Naver;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class DataConverter {
    private static final Logger logger = LoggerFactory.getLogger(DataConverter.class);

    public static <P extends ModelMapper> JsonObject convertModelToJsonObject(P property) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        return (JsonObject) parser.parse(gson.toJson(property));
    }

    public static String convertPostFixToNaverUrl(String postFix) {
        return Naver.CAFE_PREFIX.concat(postFix);
    }

    public static String convertNameToValidFileName(String desiredFileName) {
        return desiredFileName.replaceAll(CustomValidator.FILE_NAME_INVALID_REGEX, "");
    }
}