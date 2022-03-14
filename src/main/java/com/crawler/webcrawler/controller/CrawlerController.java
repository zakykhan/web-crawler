package com.crawler.webcrawler.controller;

import com.crawler.webcrawler.crawlerDTO.CrawlerDTO;
import com.crawler.webcrawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @PostMapping("/crawl")
    public String startCrawling(@RequestBody CrawlerDTO requestUrl){
        System.out.println("Request URL -----"+requestUrl.getUrlToCrawl());
       return crawlerService.startCrawlService(requestUrl);
    }

}
