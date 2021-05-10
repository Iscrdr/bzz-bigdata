package com.bzz.cloud.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Iscrdr
 * @description：存储页面信息
 * @email ：624003618@qq.com
 * @date ：2020-11-13 04:15
 * @modified By：
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
public class Page {

    /**
     * 页面内容
     */
    private String content;

    /**
     * 总播放量
     */
    private String allnumber;
    /**
     * 每日播放增量
     */
    private String daynumber;

    /**
     * 评论数
     */
    private String commentnumber;
    /**
     * 收藏数
     */
    private String collectnumber;
    /**
     * 点赞
     */
    private String supportnumber;
    /**
     * 踩
     */
    private String againstnumber;
    /**
     * 电视剧名称
     */
    private String tvname;

    /**
     * 电视剧id
     */
    private String tvId;

    /**
     * 页面url
     */
    private String url;
    /**
     * 子集
     */
    private String episodenumber;


    /**
     * 存储电视剧url,包含列表url和详情url
     */
    public List<String> urlList = new ArrayList<>();


    public void addUrl(String url){
        this.urlList.add(url);
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public String getTvId() {
        return tvId;
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }
}
