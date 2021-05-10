package com.bzz.cloud.flink.custormsource;
import java.util.Date;

import com.bzz.cloud.flink.utils.DBUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: bzz-bigdata
 * @description: 自定义flink之mysql数据源，从mysql数据读取数据
 * @author: 624003618@qq.com
 * @create: 2019-08-12 19:30
 */
public class MysqlSource extends RichSourceFunction<String> {

    PreparedStatement ps;
    private Connection connection;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = DBUtil.getConnection();
        String sql = "select * from c_cust_sale_2019 limit 5";
        ps = this.connection.prepareStatement(sql);
    }

    @Override
    public void run(SourceContext<String> ctx) throws Exception {

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            CCustSale cc = new CCustSale();
            cc.setId(resultSet.getString("id"));
            cc.setGongchang(resultSet.getString("gongchang"));
            cc.setDaqu(resultSet.getString("daqu"));
            cc.setChengshi(resultSet.getString("chengshi"));
            cc.setYewuyuan(resultSet.getString("yewuyuan"));
            cc.setCustNo(resultSet.getString("cust_no"));
            cc.setCustName(resultSet.getString("cust_name"));
            cc.setDapinleimiaoshu(resultSet.getString("dapinleimiaoshu"));
            cc.setYijipinleimiaoshu(resultSet.getString("yijipinleimiaoshu"));
            cc.setErjipinleimiaoshu(resultSet.getString("erjipinleimiaoshu"));
            cc.setSanjipinleimiaoshu(resultSet.getString("sanjipinleimiaoshu"));
            cc.setChanpinxianmiaoshu(resultSet.getString("chanpinxianmiaoshu"));
            cc.setWuliaobianma(resultSet.getString("wuliaobianma"));
            cc.setWuliaomiaoshu(resultSet.getString("wuliaomiaoshu"));
            cc.setXiang(resultSet.getDouble("xiang"));
            cc.setDun(resultSet.getDouble("dun"));
            cc.setXiaoshoushouru(resultSet.getDouble("xiaoshoushouru"));
            cc.setJingzhi(resultSet.getDouble("jingzhi"));
            cc.setShuie(resultSet.getDouble("shuie"));
            cc.setZhanlvjine(resultSet.getDouble("zhanlvjine"));
            cc.setZhekoujine(resultSet.getDouble("zhekoujine"));
            cc.setZhekoubili(resultSet.getDouble("zhekoubili"));
            cc.setShoudafangjiancheng(resultSet.getString("shoudafangjiancheng"));
            cc.setFapiaoshiqi(resultSet.getDate("fapiaoshiqi"));
            cc.setDingdanbianhao(resultSet.getString("dingdanbianhao"));
            cc.setDanjuriqi(resultSet.getDate("danjuriqi"));
            cc.setKucundidian(resultSet.getString("kucundidian"));
            cc.setCaigoubianhao(resultSet.getString("caigoubianhao"));


            cc.setRemarks(resultSet.getString("remarks"));
            cc.setCreateBy(resultSet.getString("create_by"));
            cc.setCreateDate(resultSet.getDate("create_date"));
            cc.setUpdateBy(resultSet.getString("update_by"));
            cc.setUpdateDate(resultSet.getDate("update_date"));
            cc.setDelFlag(resultSet.getString("del_flag"));
            ctx.collect(cc.toString());
        }

    }

    @Override
    public void cancel() {

    }

    /**
     * 闭连接和释放资源
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
        super.close();
    }


}
