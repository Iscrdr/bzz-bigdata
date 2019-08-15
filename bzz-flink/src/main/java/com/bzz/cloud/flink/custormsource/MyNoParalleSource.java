package com.bzz.cloud.flink.custormsource;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @program: bzz-bigdata
 * @description: 自定义Source
 * @author: 624003618@qq.com
 * @create: 2019-08-04 19:18
 */
public class MyNoParalleSource implements SourceFunction {

    @Override
    public void run(SourceContext ctx) throws Exception {

    }

    @Override
    public void cancel() {

    }
}
