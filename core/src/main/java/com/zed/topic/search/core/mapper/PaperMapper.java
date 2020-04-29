package com.zed.topic.search.core.mapper;

import com.zed.topic.search.core.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
  * @author Zed
  * @date 2020/4/28 下午9:20
  * @contact shadowl91@163.com
  */
@Mapper
public interface PaperMapper extends MyMapper<Paper> {
    int batchInsert(@Param("list") List<Paper> list);
}