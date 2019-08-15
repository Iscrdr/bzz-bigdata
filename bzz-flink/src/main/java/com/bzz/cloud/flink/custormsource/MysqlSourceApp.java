package com.bzz.cloud.flink.custormsource;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @program: bzz-bigdata
 * @description: 测试flink之mysql数据源
 * @author: 624003618@qq.com
 * @create: 2019-08-12 19:58
 */
public class MysqlSourceApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.addSource(new MysqlSource()).filter((FilterFunction<CCustSale>) cc -> cc.getCustName().contains("北京")?true:false).print();
        env.execute("MysqlSourceApp");



    }
}
