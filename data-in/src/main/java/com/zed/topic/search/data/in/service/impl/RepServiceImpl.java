package com.zed.topic.search.data.in.service.impl;

import com.zed.topic.search.core.pojo.Keywords;
import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.core.repo.KeyWordsRepo;
import com.zed.topic.search.core.repo.PaperRepo;
import com.zed.topic.search.data.in.service.RepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Zed
 * @date 2020/4/28 下午9:27
 * @contact shadowl91@163.com
 */
@Service
@Slf4j
public class RepServiceImpl implements RepService {

    @Autowired
    private PaperRepo paperRepo;

    @Autowired
    private KeyWordsRepo keyWordsRepo;

    @Override
    public int batchInsertPaper(List<Paper> list) {
        try {
            paperRepo.saveAll(list);
        } catch (Exception e) {
//            log.info("insert exception");
        }
        return list.size();
    }

    @Override
    public Paper getPaper1() {
        return paperRepo.getOnePaper();
    }

    @Override
    public List<Paper> getPaper10() {
        List<Paper> papers = paperRepo.getTenPaper();
        if (papers.size() < 10) {
            log.error("[get top 10 papers - error] paper.size={}",papers.size());
            return null;
        }
        return papers;
    }

    @Override
    public int insertKeyword(String keyword) {
        Keywords keywords = new Keywords();
        keywords.setKeyword(keyword);
        Keywords target = keyWordsRepo.save(keywords);
//        Keywords target = this.searchKeyWord(keywords);
        if (target == null)
            return -1;
        else return 1;
    }

    @Override
    public Keywords searchKeyWord(Keywords keywords) {
       return keyWordsRepo.findByKeyword(keywords.getKeyword());
    }
}
