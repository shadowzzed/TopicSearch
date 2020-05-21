package com.zed.topic.search.data.out.controller;

import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.data.out.pojo.YearData;
import com.zed.topic.search.data.out.service.DataOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zed
 * @date 2020/5/21 上午1:52
 * @contact shadowl91@163.com
 */
@RestController
@Slf4j
public class MyController {

    @Autowired
    DataOutService dataOutService;

    @GetMapping("/api/year")
    public List<YearData> getData5Year(String keyword) {
        List<YearData> list = new ArrayList<>();
        int num2020 = dataOutService.get2020(keyword);
        YearData yearData = new YearData();
        yearData.setNum(num2020);
        yearData.setYear("2020");
        list.add(yearData);
        int num2019 = dataOutService.get2019(keyword);
        YearData yearData1 = new YearData();
        yearData1.setYear("2019");
        yearData1.setNum(num2019);
        list.add(yearData1);
        int num2018 = dataOutService.get2018(keyword);
        YearData yearData2 = new YearData();
        yearData2.setYear("2018");
        yearData2.setNum(num2018);
        list.add(yearData2);
        int num2017 = dataOutService.get2017(keyword);
        YearData yearData3 = new YearData();
        yearData3.setNum(num2017);
        yearData3.setYear("2017");
        list.add(yearData3);
        YearData yearData4 = new YearData();
        int num2016 = dataOutService.get2016(keyword);
        yearData4.setNum(num2016);
        yearData4.setYear("2016");
        list.add(yearData4);
        return list;
    }

    @GetMapping("/api/10")
    public List<Paper> getTop10(String keyword) {
        List<Paper> list = dataOutService.getTopTenPaperByKeyword(keyword);
        if (list.size() < 10) {
            log.error("list size = {}", list.size());
            return null;
        }
        return list;

    }
}
