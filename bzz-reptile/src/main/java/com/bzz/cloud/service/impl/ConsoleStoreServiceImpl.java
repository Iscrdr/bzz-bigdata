package com.bzz.cloud.service.impl;

import com.bzz.cloud.entity.Page;
import com.bzz.cloud.service.IStoreService;

/**
 * @author ：Iscrdr
 * @description：
 * @email ：624003618@qq.com
 * @date ：2020-11-13 20:10
 * @modified By：
 * @version:
 */
public class ConsoleStoreServiceImpl implements IStoreService {
    @Override
    public void store(Page page) {
        System.out.println(page.toString());
    }
}
