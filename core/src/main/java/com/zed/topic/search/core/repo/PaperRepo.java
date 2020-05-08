package com.zed.topic.search.core.repo;

import com.zed.topic.search.core.pojo.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Zed
 * @date 2020/5/7 下午11:29
 * @contact shadowl91@163.com
 */
@Repository
public interface PaperRepo extends JpaRepository<Paper, String> {
    @Query(value = "SELECT * FROM topic_search.paper limit 1", nativeQuery = true)
    Paper getOnePaper();
}
