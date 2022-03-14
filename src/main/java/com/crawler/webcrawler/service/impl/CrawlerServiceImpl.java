package com.crawler.webcrawler.service.impl;

import com.crawler.webcrawler.crawlerDTO.CrawlerDTO;
import com.crawler.webcrawler.service.CrawlerService;
import org.apache.http.HttpStatus;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    private static final int MAX_DEPTH = 2;

    @Override
    @Async
    public String startCrawlService(CrawlerDTO crawlerUrl) {

        int statusCode = startCrawl(0, crawlerUrl.getUrlToCrawl(), new ArrayList<>());
        if(statusCode == 200){
            return "SUCCESS";
        }

        return null;
    }

    private static int startCrawl(int depth, String url, ArrayList<String> layerVisited) {
        if(depth <= MAX_DEPTH){
            Document request = documentRequest(url, layerVisited);

            if(request != null){
                for(Element e : request.select("a[href]")){
                    String subUrl = e.absUrl("href");
                    if(!layerVisited.contains(subUrl)){
                        startCrawl(depth++, subUrl, layerVisited);
                    }
                }
                return HttpStatus.SC_OK;
            }
        }
        return 0;
    }

    private static Document documentRequest(String url, ArrayList<String> value){
        try {
            Connection con = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true);
            Document document = con.get();
            if(con.response().statusCode() == 200){
                System.out.println("Link: " + url);
                System.out.println("Title: " + document.title());
                System.out.println("Location: " + document.location());
                System.out.println("Paragraph: " + document.select("p"));
                value.add(url);

                return document;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
