package com.zed.topic.search.core.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Zed
 * @date 2020/5/2 下午3:35
 * @contact shadowl91@163.com
 */
@Data
public class PaperList {
    private String table_name;

    private List<Paper> list;
}
