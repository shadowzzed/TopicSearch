package com.zed.topic.search.nlp.service;

import java.io.IOException;
import java.util.List;

/**
 * @author Zed
 * @date 2020/3/30 下午3:37
 * @contact shadowl91@163.com
 */
public interface IKService {
    List<String> getCuts(String msg) throws IOException;
}
