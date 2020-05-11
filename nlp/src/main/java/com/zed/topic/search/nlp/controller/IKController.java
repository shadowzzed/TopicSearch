package com.zed.topic.search.nlp.controller;

import com.alibaba.fastjson.JSON;
import com.zed.topic.search.nlp.service.IKService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Zed
 * @date 2020/3/30 下午3:42
 * @contact shadowl91@163.com
 */
@RestController
@RefreshScope
@Slf4j
public class IKController {

    @Autowired
    private IKService ikService;

    @GetMapping("/str/ch/{msg}")
    public String getCuts(@PathVariable("msg") String msg) throws IOException {
        List<String> list = ikService.getCuts(msg);
        log.info("list.size = {}, list = {}", list.size(), JSON.toJSONString(list));
        return JSON.toJSONString(list);
    }

    @GetMapping("/str/en/{msg}")
    public String getCutsEn(@PathVariable("msg") String msg) throws IOException {
        List<String> list = ikService.getCuts(msg);
        return JSON.toJSONString(list);
    }
}
