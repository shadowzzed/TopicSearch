package com.zed.topic.search.core.mapper;

import com.zed.topic.search.core.pojo.Paper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zed
 * @date 2020/4/28 下午9:28
 * @contact shadowl91@163.com
 */
@SpringBootTest
public class PaperMapperTest {
    @Autowired
    PaperMapper mapper;

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
        List<Paper> list = new ArrayList<>();
        list.add(paper);
        mapper.batchInsert(list);
    }
}