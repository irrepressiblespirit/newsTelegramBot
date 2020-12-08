package com.skibnev.telegrambot.newstelegrambot.external_api.news;

import com.skibnev.telegrambot.newstelegrambot.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements ServiceHelper {

    private final RSSChannelReader rssChannelReader;
    private final NewsConvertor newsConvertor;

    @Autowired
    public NewsService(RSSChannelReader rssChannelReader, NewsConvertor newsConvertor) {
        this.rssChannelReader = rssChannelReader;
        this.newsConvertor = newsConvertor;
    }

    @Override
    public String getInfo(String origin) {
        return newsConvertor.convert(rssChannelReader.read());
    }
}
