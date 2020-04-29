package com.zed.topic.search.data.in.service;

import com.zed.topic.search.core.pojo.Paper;

import java.util.List;

/**
 * @author Zed
 * @date 2020/4/28 下午9:27
 * @contact shadowl91@163.com
 */
public interface RepService {
    int batchInsert(List<Paper> list);
}
