package com.bzz.cloud.service.impl;

import com.bzz.cloud.entity.Page;
import com.bzz.cloud.service.IDownLoadService;
import com.bzz.cloud.utils.PageDownloadUtil;

/**
 * @author ：Iscrdr
 * @description：页面下载实现
 * @email ：624003618@qq.com
 * @date ：2020-11-13 04:29
 * @modified By：
 * @version:
 */
public class HttpClientDownLoadServiceImpl implements IDownLoadService {
    @Override
    public Page download(String url) {
        Page page = new Page();
        page.setContent(PageDownloadUtil.getPageContent(url));
        return page;
    }
}
