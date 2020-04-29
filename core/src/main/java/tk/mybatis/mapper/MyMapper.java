package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Zed
 * @date 2020/4/28 下午9:23
 * @contact shadowl91@163.com
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
