package com.bzz.cloud.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Author : yang qianli
 * @email: 624003618@qq.com
 * @Date: 2019-05-30 13-22
 * @Modified by:
 * @Description:
 */
public class BaseWriteModel  extends BaseRowModel {
    @ExcelProperty(value = {"表头1","表头1","表头31"},index = 0)
    protected String p1;

    @ExcelProperty(value = {"表头1","表头1","表头32"},index = 1)
    protected String p2;

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }
}
