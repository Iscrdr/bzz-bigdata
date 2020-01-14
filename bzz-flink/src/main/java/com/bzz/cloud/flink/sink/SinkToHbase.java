package com.bzz.cloud.flink.sink;

import com.bzz.cloud.flink.custormsource.CCustSale;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @program: bzz-bigdata
 * @description: flink批量写入hbase
 * @author: 624003618@qq.com
 * @create: 2019-10-24 19:07
 */
public class SinkToHbase extends RichSinkFunction<List<CCustSale>>  {


    private org.apache.hadoop.conf.Configuration conf;
    private Connection connection ;
    private Admin admin ;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop-03:2181,hadoop-04:2181,hadoop-05:2181");
        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();
    }

    public void createTable(String tableName) throws IOException {
        NamespaceDescriptor namespace = NamespaceDescriptor.create("hadoop111").build();
        admin.createNamespace(namespace);
        HTableDescriptor table = new HTableDescriptor(Bytes.toBytes(tableName));
        HColumnDescriptor family1 = new HColumnDescriptor(Bytes.toBytes("info"));
        HColumnDescriptor family2 = new HColumnDescriptor(Bytes.toBytes("zhibiao"));
        HColumnDescriptor family3 = new HColumnDescriptor(Bytes.toBytes("remark"));
        table.addFamily(family1);
        table.addFamily(family2);
        table.addFamily(family3);
        admin.createTable(table);

    }
    public Put getPut(Put put,CCustSale c) throws IOException {

        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("gongchang"), Bytes.toBytes(c.getGongchang()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("daqu"), Bytes.toBytes(c.getDaqu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("chengshi"), Bytes.toBytes(c.getChengshi()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("yewuyuan"), Bytes.toBytes(c.getYewuyuan()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("custNo"), Bytes.toBytes(c.getCustNo()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("custName"), Bytes.toBytes(c.getCustName()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("dapinleimiaoshu"), Bytes.toBytes(c.getDapinleimiaoshu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("yijipinleimiaoshu"), Bytes.toBytes(c.getYijipinleimiaoshu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("erjipinleimiaoshu"), Bytes.toBytes(c.getErjipinleimiaoshu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("sanjipinleimiaoshu"), Bytes.toBytes(c.getSanjipinleimiaoshu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("chanpinxianmiaoshu"), Bytes.toBytes(c.getChanpinxianmiaoshu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("wuliaobianma"), Bytes.toBytes(c.getWuliaobianma()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("wuliaomiaoshu"), Bytes.toBytes(c.getWuliaomiaoshu()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("shoudafangjiancheng"), Bytes.toBytes(c.getShoudafangjiancheng()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("dingdanbianhao"), Bytes.toBytes(c.getDingdanbianhao()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("kucundidian"), Bytes.toBytes(c.getKucundidian()));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("caigoubianhao"), Bytes.toBytes(c.getCaigoubianhao()));


        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("xiang"), Bytes.toBytes(c.getXiang()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("dun"), Bytes.toBytes(c.getDun()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("xiaoshoushouru"), Bytes.toBytes(c.getXiaoshoushouru()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("jingzhi"), Bytes.toBytes(c.getJingzhi()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("shuie"), Bytes.toBytes(c.getShuie()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("zhanlvjine"), Bytes.toBytes(c.getZhanlvjine()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("zhekoujine"), Bytes.toBytes(c.getZhekoujine()));
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("zhekoubili"), Bytes.toBytes(c.getZhekoubili()));
        Date danjuriqi = c.getDanjuriqi();
        danjuriqi = danjuriqi==null?new Date():danjuriqi;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String danjuriqiFormat = sdf.format(danjuriqi);
        put.addColumn(Bytes.toBytes("zhibiao"), Bytes.toBytes("danjuriqi"), Bytes.toBytes(danjuriqiFormat));

        put.addColumn(Bytes.toBytes("remark"), Bytes.toBytes("remark"), Bytes.toBytes(c.getRemarks()));
        put.addColumn(Bytes.toBytes("remark"), Bytes.toBytes("createBy"), Bytes.toBytes(c.getCreateBy()));
        put.addColumn(Bytes.toBytes("remark"), Bytes.toBytes("updateBy"), Bytes.toBytes(c.getUpdateBy()));
        String createDate = sdf.format(c.getCreateDate()==null?new Date():c.getCreateDate());
        put.addColumn(Bytes.toBytes("remark"), Bytes.toBytes("createDate"), Bytes.toBytes(createDate));
        String updateDate = sdf.format(c.getUpdateDate()==null?new Date():c.getUpdateDate());
        put.addColumn(Bytes.toBytes("remark"), Bytes.toBytes("updateDate"), Bytes.toBytes(updateDate));
        put.addColumn(Bytes.toBytes("remark"), Bytes.toBytes("delFlag"), Bytes.toBytes(c.getDelFlag()));

        return put;

    }


    @Override
    public void invoke(List<CCustSale> value, Context context) throws Exception {
        Admin admin = connection.getAdmin();
        boolean tableExists = admin.tableExists(TableName.valueOf("CCustSale"));
        if(!tableExists){
            createTable("CCustSale");
        }
        Table table = connection.getTable(TableName.valueOf("CCustSale"));
        Put put ;
        List<Put> puts = new ArrayList<>();
        for(int i=0;i<value.size();i++){
            CCustSale cCustSale = value.get(i);
            put = new Put(Bytes.toBytes(cCustSale.getId()));
            put = getPut(put, cCustSale);
            puts.add(put);
        }
        table.put(puts);
        table.close();
    }

    /**
     * 闭连接和释放资源
     * @throws Exception
     */
    @Override
    public void close() throws Exception {

        if (connection != null) {
            connection.close();
        }
        super.close();
    }


}
