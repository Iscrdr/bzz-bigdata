package com.bzz.cloud.service;

import com.bzz.cloud.entity.Page;

/**
 * @author ：Iscrdr
 * @description：页面下载接口服务
 * @email ：624003618@qq.com
 * @date ：2020-11-13 03:48
 * @modified By：
 * @version: 1.0.0
 */
public interface IDownLoadService {

    public Page download(String url);

}
