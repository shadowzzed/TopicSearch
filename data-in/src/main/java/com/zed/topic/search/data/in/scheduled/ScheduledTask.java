package com.zed.topic.search.data.in.scheduled;

import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.data.in.service.DataInAutoService;
import com.zed.topic.search.data.in.service.RepService;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/9 上午12:10
 * @contact shadowl91@163.com
 */
@Component
public class ScheduledTask {

    @Autowired
    private DataInAutoService dataInAutoService;

    @Autowired
    private RepService repService;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 0/10 * * * ? ")
    public void dataInScheduledPerMin() {
        Paper paper = repService.getPaper1();
        if (paper == null) {
            this.dataInScheduledPerHour();
            return;
        }
        String summary = paper.getSummary();
        if (!StringUtils.isEmpty(summary))
            dataInAutoService.dataInByKeyword(summary);
        else
            this.dataInScheduledPerHour();
    }

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void dataInScheduledPerHour() {
        List<Paper> papers = repService.getPaper10();
        if (papers == null)
            return;
        for (Paper paper: papers) {
            String summary = paper.getSummary();
            dataInAutoService.dataInByKeyword(summary);
        }
    }
}
