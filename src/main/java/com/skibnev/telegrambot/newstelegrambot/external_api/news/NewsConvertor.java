package com.skibnev.telegrambot.newstelegrambot.external_api.news;

import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
public class NewsConvertor {

    public String convert(Map<String, String> newsFeed) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry:newsFeed.entrySet()) {
            stringBuilder.append(System.lineSeparator()).append(entry.getKey()).append(" : ").append(entry.getValue());
        }
        return stringBuilder.toString();
    }
}
