package com.zed.topic.search.data.in.service.impl;

import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.data.in.service.RepService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zed
 * @date 2020/4/28 下午9:33
 * @contact shadowl91@163.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RepServiceImplTest {

    @Autowired
    RepService repService;

    @Test
    public void batchInsert() {
        Paper paper = new Paper();
        paper.setAuthor("test author");
        paper.setDate("2020-04-27");
        paper.setDb("science");
        paper.setDownloads(10);
        paper.setFilecode("testcode");
        paper.setName("testname");
        paper.setRef(10);
        paper.setSource("111");
        paper.setSummary("ddd");
        paper.setKeyword("keyword");
        List<Paper> list = new ArrayList<>();
        list.add(paper);
        repService.batchInsertPaper(list);
    }

    @Test
    public void test() {
        System.out.println(repService.findPaperByFileCode("11"));
    }
}