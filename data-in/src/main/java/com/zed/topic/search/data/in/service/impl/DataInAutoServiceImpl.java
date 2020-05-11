package com.zed.topic.search.data.in.service.impl;

import com.alibaba.fastjson.JSON;
import com.zed.topic.search.core.configuration.Config;
import com.zed.topic.search.core.pojo.Keywords;
import com.zed.topic.search.data.in.service.DataInAutoService;
import com.zed.topic.search.data.in.service.RepService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/9 上午12:14
 * @contact shadowl91@163.com
 */
@Service
@Slf4j
public class DataInAutoServiceImpl implements DataInAutoService {

    @Autowired
    private Config config;

    @Autowired
    private RepService repService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void dataInByKeyword(String keyword) {
        if (keyword.length() < 5) {
            this.insertKeywordAndPaper(keyword);
        }
        String keywords_extra = this.getDataNLP(keyword);
        List<String> list = JSON.parseObject(keywords_extra, List.class);
        log.info("[get keywords] get list.size={}", list.size());
        if (list.size() < 1) {
            log.error("[get keywords], list is null");
            return;
        }
        list.forEach(str -> {
            log.info("[data in] keyword = {}", String.valueOf(str));
            this.insertKeywordAndPaper(String.valueOf(str));
        });

    }

    private void insertKeywordAndPaper(String keyword) {
        if (this.searchAndInsertKeyword(keyword))
            this.getDataWorm(keyword);
    }

    private String getDataWorm(String keyword) {
        String response = this.getDataWormDetail(keyword, 0);
        log.info("[data in - get worm data] response = {}", response);
        return response;
    }

    private String getDataNLP(String keyword) {
        String response = this.getDataNLPDetail(keyword, 0);
        log.info("[data in - get nlp data] response = {}", response);
        return response;
    }

    private String getDataWormDetail(String keyword, int count) {
        String response = "";
        if (keyword.contains(".") || keyword.startsWith("1") || keyword.startsWith("2") || keyword.startsWith("3") ||
        keyword.startsWith("4") || keyword.startsWith("5") || keyword.startsWith("6") || keyword.startsWith("7") ||
        keyword.startsWith("8") || keyword.startsWith("9"))
            return null;
        try {
            count++;
            if (count > 5)
                return null;
            log.info("[data in - worm]fail and retry,keyword = {}, times = {}", keyword, count);
            response = restTemplate.getForObject("http://worm-provider/worm/" + keyword, String.class);

        } catch (Exception e) {
            getDataWormDetail(keyword, count);
        }
        return response;
    }

    private String getDataNLPDetail(String keyword, int count) {
        String response = "" ;
        try {
            count++;
            if (count > 5)
                return null;
            log.info("[data in - nlp]fail and retry,keyword{}, times = {}", keyword, count);
            response = restTemplate.getForObject("http://ik-provider/str/ch/" + keyword, String.class);

        } catch (Exception e) {
            getDataNLPDetail(keyword, count);
        }
        return response;
    }

    private boolean searchAndInsertKeyword(String keyword) {
        Keywords keywords = new Keywords();
        keywords.setKeyword(keyword);
        Keywords target = repService.searchKeyWord(keywords);
        if (target == null) {
            repService.insertKeyword(keyword);
            return true;
        }
        log.info("keyword = {}, has existed", keyword);
        return false;
    }
}
