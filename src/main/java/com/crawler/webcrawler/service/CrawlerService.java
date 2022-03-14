package com.crawler.webcrawler.service;

import com.crawler.webcrawler.crawlerDTO.CrawlerDTO;
import org.springframework.stereotype.Service;

@Service
public interface CrawlerService {

    String startCrawlService(CrawlerDTO crawlerUrl);
}
