package com.zed.topic.search.data.out.service.impl;

import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.core.repo.PaperRepo;
import com.zed.topic.search.data.out.service.DataOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/8 下午1:42
 * @contact shadowl91@163.com
 */
@Service
public class DataOutServiceImpl implements DataOutService {
    @Autowired
    private PaperRepo paperRepo;

    @Override
    public List<Paper> getTopTenPaperByKeyword(String keyword) {
        return paperRepo.getTopTenPaperByKeyword(keyword);
    }

    @Override
    public int get2020(String keyword) {
        return paperRepo.getCount2020("%" + keyword + "%");
    }

    @Override
    public int get2019(String keyword) {
        return paperRepo.getCount2019("%" + keyword + "%");
    }

    @Override
    public int get2018(String keyword) {
        return paperRepo.getCount2018("%" + keyword + "%");
    }

    @Override
    public int get2017(String keyword) {
        return paperRepo.getCount2017("%" + keyword + "%");
    }

    @Override
    public int get2016(String keyword) {
        return paperRepo.getCount2016("%" + keyword + "%");
    }
}
