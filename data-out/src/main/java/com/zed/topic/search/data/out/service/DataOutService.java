package com.zed.topic.search.data.out.service;

import com.zed.topic.search.core.pojo.Paper;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/8 下午1:42
 * @contact shadowl91@163.com
 */
public interface DataOutService {
    List<Paper> getTopTenPaperByKeyword(String keyword);

    int get2020(String keyword);

    int get2019(String keyword);

    int get2018(String keyword);

    int get2017(String keyword);

    int get2016(String keyword);
}
