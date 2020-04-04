package com.zed.topic.search.data.in.service.impl;

import com.zed.topic.search.data.in.service.WormService;
import com.zed.topic.search.data.in.worm.WebReptileCNKI;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Zed
 * @date 2020/4/4 下午4:02
 * @contact shadowl91@163.com
 */
@Service
public class WormServiceImpl implements WormService {

    @Autowired
    private WebReptileCNKI worm;

    @Override
    public boolean getPapers(String keyword) {
        if (StringUtils.isEmpty(keyword))
            return false;
        worm.getCNKIContent(keyword);
        return true;
    }
}
