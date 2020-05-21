package com.zed.topic.search.core.repo;

import com.zed.topic.search.core.pojo.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/7 下午11:29
 * @contact shadowl91@163.com
 */
@Repository
public interface PaperRepo extends JpaRepository<Paper, String> {
    @Query(value = "SELECT * FROM topic_search.paper order by insert_dt desc limit 1", nativeQuery = true)
    Paper getOnePaper();

    @Query(value = "SELECT * FROM topic_search.paper order by insert_dt desc limit 10", nativeQuery = true)
    List<Paper> getTenPaper();

    /*******************************************************************************/

    @Query(value = "select * from (select * from paper where keyword like ?) as ta order by downloads desc limit 10",nativeQuery = true)
    List<Paper> getTopTenPaperByKeyword(String keyword);

    @Query(value = "select count(*) from (select * from paper where keyword like ?) as ta where date like '2020%'", nativeQuery = true)
    int getCount2020(String keyword);

    @Query(value = "select count(*) from (select * from paper where keyword like ?) as ta where date like '2019%'", nativeQuery = true)
    int getCount2019(String keyword);

    @Query(value = "select count(*) from (select * from paper where keyword like ?) as ta where date like '2018%'", nativeQuery = true)
    int getCount2018(String keyword);

    @Query(value = "select count(*) from (select * from paper where keyword like ?) as ta where date like '2017%'", nativeQuery = true)
    int getCount2017(String keyword);

    @Query(value = "select count(*) from (select * from paper where keyword like ?) as ta where date like '2016%'", nativeQuery = true)
    int getCount2016(String keyword);


}
