package com.skibnev.telegrambot.newstelegrambot.external_api.news;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.skibnev.telegrambot.newstelegrambot.config.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class RSSChannelReader {

    private Properties.NewsRssChannelConfig newsRssChannelConfig;

    @Autowired
    public RSSChannelReader(Properties properties) {
        this.newsRssChannelConfig = properties.getNewsRssChannelConfig();
    }

    public Map<String, String> read() {
        Map<String, String> news = new HashMap<>();
        try {
            URL feedSource = new URL(newsRssChannelConfig.getChannelURL());
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            feed.getEntries().forEach(syndEntry -> news.put(syndEntry.getTitle(), syndEntry.getLink()));
        } catch (FeedException | IOException exc) {
            exc.printStackTrace();
        }
        return news;
    }
}
