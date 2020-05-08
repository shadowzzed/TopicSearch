package com.zed.topic.search.data.in.service.impl;

import com.alibaba.fastjson.JSON;
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
    private RepService repService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void dataInByKeyword(String keyword) {
        this.searchAndInsertKeyword(keyword);
        String response = restTemplate.getForObject("http://127.0.0.1:8082/worm/" + keyword, String.class);
        if (!StringUtils.isEmpty(response) && response.equals("success")) {
            String keywords_extra = restTemplate.getForObject("http://127.0.0.1:8081/str/ch/" + keyword, String.class);
            List<String> list = JSON.parseObject(keywords_extra, List.class);
            log.info("[get keywords] get list.size={}",list.size());
            if (list == null) {
                log.error("[get keywords], list is null");
                return;
            }
            list.forEach(str -> {
                this.searchAndInsertKeyword(String.valueOf(str));
                restTemplate.getForObject("http://127.0.0.1:8082/worm/" + str, String.class);
            });
        }
    }

    private void searchAndInsertKeyword(String keyword) {
        Keywords keywords = new Keywords();
        keywords.setKeyword(keyword);
        Keywords target = repService.searchKeyWord(keywords);
        if (target == null)
            repService.insertKeyword(keyword);
    }
}
