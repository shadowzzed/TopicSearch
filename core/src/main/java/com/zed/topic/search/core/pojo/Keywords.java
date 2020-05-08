package com.zed.topic.search.core.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
  * @author Zed
  * @date 2020/4/29 下午5:08
  * @contact shadowl91@163.com
  */
@Data
@Entity
public class Keywords implements Serializable {
    @Id
    private Integer id;

    private String keyword;

    private static final long serialVersionUID = 1L;
}