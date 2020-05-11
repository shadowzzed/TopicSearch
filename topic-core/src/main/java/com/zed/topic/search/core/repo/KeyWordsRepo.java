package com.zed.topic.search.core.repo;

import com.zed.topic.search.core.pojo.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Zed
 * @date 2020/5/7 下午11:51
 * @contact shadowl91@163.com
 */
@Repository
public interface KeyWordsRepo extends JpaRepository<Keywords, Integer> {
    Keywords findByIdAfter(int id);
    Keywords findByKeyword(String keyword);
}
