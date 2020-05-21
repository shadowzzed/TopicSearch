package com.zed.topic.search.nlp.service.impl;

import com.zed.topic.search.nlp.service.IKService;
import com.zed.topic.search.nlp.utils.IKUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zed
 * @date 2020/4/2 下午4:52
 * @contact shadowl91@163.com
 */
@Service
@Slf4j
public class IKServiceImpl implements IKService {


    private static Pattern pattern;

    static {
        pattern = Pattern.compile("[\\w]");
    }

    public List<String> getCuts(String msg) throws IOException {
        if (StringUtils.isEmpty(msg))
            return null;
        List<String> list = IKUtil.cut(msg);
        log.info("msg = {}, list.size = {}", msg, list.size());
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (StringUtils.isEmpty(str))
                continue;
            Matcher matcher = pattern.matcher(str.charAt(0) + "");
            if (matcher.matches()) {
                iterator.remove();
                log.info("[ik service] drop keyword = {}", str);
            }
        }
        return list;
    }
}
