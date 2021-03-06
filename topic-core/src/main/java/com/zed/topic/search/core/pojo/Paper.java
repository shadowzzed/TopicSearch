package com.zed.topic.search.core.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
  * @author Zed
  * @date 2020/5/7 下午8:55
  * @contact shadowl91@163.com
  */
@Data
@Entity
public class Paper implements Serializable {
    @Id
    private String filecode;

    private String name;

    private String summary;

    private String author;

    private String source;

    private String db;

    private String date;

    private Integer ref;

    private Integer downloads;

    private String keyword;

    @Column(name="insert_dt",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private String insert_dt;

    private static final long serialVersionUID = 1L;
}