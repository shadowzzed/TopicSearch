package com.zed.topic.search.data.in.service.impl;

import com.alibaba.fastjson.JSON;
import com.zed.topic.search.core.configuration.Config;
import com.zed.topic.search.core.pojo.Keywords;
import com.zed.topic.search.data.in.config.DataInConfiguration;
import com.zed.topic.search.data.in.service.DataInAutoService;
import com.zed.topic.search.data.in.service.RepService;
import com.zed.topic.search.data.in.worm.WebReptileCNKI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zed
 * @date 2020/5/9 上午12:14
 * @contact shadowl91@163.com
 */
@Service
@Slf4j
public class DataInAutoServiceImpl implements DataInAutoService {

    @Autowired
    private DataInConfiguration config;

    @Autowired
    private RepService repService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebReptileCNKI webReptileCNKI;

    private static Pattern pattern;

    static {
        pattern = Pattern.compile("[\\w]");
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void dataInByKeyword(String keyword) {
        executorService.execute(new MyRunnable(keyword));
    }

    private void getDataSearchAndInsertKeyword(String keyword) {
        if (keyword.length() < 5) {
            this.insertKeywordAndPaper(keyword);
        }
        String keywords_extra = this.getDataNLP(keyword);
        if (StringUtils.isEmpty(keywords_extra))
            return;
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

    class MyRunnable implements Runnable {
        private String keyword;

        public MyRunnable(String keyword) {
            this.keyword = keyword;

        }

        @Override
        public void run() {
            getDataSearchAndInsertKeyword(keyword);
        }
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
        if (StringUtils.isEmpty(keyword))
            return null;
        try {
            count++;
            if (count > 5)
                return null;
            log.info("[data in - worm]fail and retry,keyword = {}, times = {}", keyword, count);
            webReptileCNKI.getCNKIContent(keyword);
            //            String url = "http://"+ config.getWormAdd() + ":" + config.getWormPort() +"/worm/" + URLEncoder.encode(keyword, "utf-8");
//            response = restTemplate.getForObject(url, String.class);
            response = "success";
        } catch (Exception e) {
            log.error(e.getMessage());
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
            String url = "http://"+ config.getNlpAdd() + ":" + config.getNlpPort() + "/str/ch/" + URLEncoder.encode(keyword, "utf-8");
            log.info("get keywords, url = {}", url);
            response = restTemplate.getForObject(url, String.class);


        } catch (Exception e) {
            e.printStackTrace();
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
