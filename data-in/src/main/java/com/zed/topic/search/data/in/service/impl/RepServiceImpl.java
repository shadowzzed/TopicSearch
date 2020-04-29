package com.zed.topic.search.data.in.service.impl;

import com.zed.topic.search.core.mapper.PaperMapper;
import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.data.in.service.RepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zed
 * @date 2020/4/28 下午9:27
 * @contact shadowl91@163.com
 */
@Service
public class RepServiceImpl implements RepService {
    @Autowired
    private PaperMapper mapper;

    @Override
    public int batchInsert(List<Paper> list) {
        if (list.size() < 1)
            return -1;
        mapper.batchInsert(list);
        return list.size();
    }
}
