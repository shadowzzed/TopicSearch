package com.zed.topic.search.data.in.controller;

import com.alibaba.fastjson.JSON;
import com.zed.topic.search.core.pojo.Keywords;
import com.zed.topic.search.data.in.service.DataInAutoService;
import com.zed.topic.search.data.in.service.RepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author Zed
 * @date 2020/4/29 下午4:42
 * @contact shadowl91@163.com
 */
@RestController
@Slf4j
public class DataInController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RepService repService;

    @Autowired
    private DataInAutoService dataInAutoService;

    @GetMapping("/data/in/{keyword}")
    public String dataIn(@PathVariable("keyword") String keyword) throws UnsupportedEncodingException {
        keyword = URLDecoder.decode(keyword, "utf-8");
        dataInAutoService.dataInByKeyword(keyword);
        return "success";
    }
}
