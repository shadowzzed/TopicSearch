package com.zed.topic.search.data.in.controller;

import com.zed.topic.search.data.in.service.WormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zed
 * @date 2020/4/4 下午3:58
 * @contact shadowl91@163.com
 */
@RestController
public class WebCrawlerController {

    @Autowired
    WormService wormService;

    @GetMapping("/worm/{keyword}")
    public String rep(@PathVariable("keyword") String keyword) {
        boolean flag = wormService.getPapers(keyword);
        if (flag)
            return "success";
        else return "fail";
    }

}
