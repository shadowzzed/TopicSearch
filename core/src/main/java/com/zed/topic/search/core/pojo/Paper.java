package com.zed.topic.search.core.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
  * @author Zed
  * @date 2020/4/28 下午9:20
  * @contact shadowl91@163.com
  */
@Table(name = "paper")
public class Paper implements Serializable {
    @Id
    @Column(name = "filecode")
    private String filecode;

    @Column(name = "`name`")
    private String name;

    @Column(name = "summary")
    private String summary;

    @Column(name = "author")
    private String author;

    @Column(name = "`source`")
    private String source;

    @Column(name = "db")
    private String db;

    @Column(name = "`date`")
    private String date;

    @Column(name = "`ref`")
    private Integer ref;

    @Column(name = "downloads")
    private Integer downloads;

    private static final long serialVersionUID = 1L;

    /**
     * @return filecode
     */
    public String getFilecode() {
        return filecode;
    }

    /**
     * @param filecode
     */
    public void setFilecode(String filecode) {
        this.filecode = filecode;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return db
     */
    public String getDb() {
        return db;
    }

    /**
     * @param db
     */
    public void setDb(String db) {
        this.db = db;
    }

    /**
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return ref
     */
    public Integer getRef() {
        return ref;
    }

    /**
     * @param ref
     */
    public void setRef(Integer ref) {
        this.ref = ref;
    }

    /**
     * @return downloads
     */
    public Integer getDownloads() {
        return downloads;
    }

    /**
     * @param downloads
     */
    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }
}