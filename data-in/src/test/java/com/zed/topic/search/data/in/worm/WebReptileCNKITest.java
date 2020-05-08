package com.zed.topic.search.data.in.worm;

import com.zed.topic.search.core.configuration.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zed
 * @date 2020/4/4 下午4:41
 * @contact shadowl91@163.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebReptileCNKITest {

    @Autowired
    WebReptileCNKI cnki;

    @Autowired
    Config config;

    @Test
    public void test1() {
        System.out.println(config.cnkiURL);
    }
    @Test
    public void getCNKIContent() {
        cnki.getCNKIContent("中文分词");
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
//        list.clear();
        list = new ArrayList<>(10);
        System.out.println(list.size());
    }
}