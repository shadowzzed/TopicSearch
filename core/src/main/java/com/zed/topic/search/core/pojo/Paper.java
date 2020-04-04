package com.zed.topic.search.core.pojo;

import lombok.Data;

/**
 * @author Zed
 * @date 2020/4/3 下午1:30
 * @contact shadowl91@163.com
 */
@Data
public class Paper {

    // code
    private String filecode;

    // file name
    private String name;

    // summary
    private String summary;

    // author split by ';'
    private String author;

    // source
    private String source;

    // stored db on cnki
    private String db;

    // date
    private String date;

    // times
    private String ref;

    // times
    private String downloads;
}
