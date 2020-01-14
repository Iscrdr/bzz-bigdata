package com.bzz.cloud;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * 创建namespace
     * @throws IOException
     */
    @Test
    public void testCreateNamespace() throws IOException {
        //1.读取配置文件，先读取默认的配置文件，在读取自定义的配置文件
        Configuration conf = HBaseConfiguration.create();


        //2.建立连接
        Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();

        //创建namespace
        NamespaceDescriptor namespace = NamespaceDescriptor.create("hadoop111").build();
        admin.createNamespace(namespace);

    }
    @Test
    public void testCreateTable() throws IOException {
        //1.读取配置文件，先读取默认的配置文件，在读取自定义的配置文件
        Configuration conf = HBaseConfiguration.create();


        //2.建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();

        /*TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf("t3"));
        ColumnFamilyDescriptorBuilder cfd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info"));
        tableDescriptorBuilder.setColumnFamily(cfd.build());


        admin.createTable(tableDescriptorBuilder.build());*/
    }
    @Test
    public void testDelTable() throws IOException {
        //1.读取配置文件，先读取默认的配置文件，在读取自定义的配置文件
        Configuration conf = HBaseConfiguration.create();


        //2.建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        HTable t2 = (HTable) connection.getTable(TableName.valueOf("t2"));
        Admin admin = connection.getAdmin();
        TableName t21 = TableName.valueOf("t2");
        admin.disableTable(t21);
        admin.deleteTable(t21);
        connection.close();
    }

    /**
     * 添加数据
     * @throws IOException
     */
    @Test
    public void testAddFamilly() throws IOException {
        //1.读取配置文件，先读取默认的配置文件，在读取自定义的配置文件
        Configuration conf = HBaseConfiguration.create();


        //2.建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        HTable t2 = (HTable) connection.getTable(TableName.valueOf("t3"));

        //TableDescriptor descriptor = t2.getDescriptor();
        //ColumnFamilyDescriptor[] columnFamilies = descriptor.getColumnFamilies();
        /*for(ColumnFamilyDescriptor cfs : columnFamilies){
            String info = cfs.getNameAsString();
            List<Put> puts = new ArrayList<Put>();
            Put put1 = new Put(Bytes.toBytes("t001"),1);
            put1.addColumn(Bytes.toBytes(info),Bytes.toBytes("name"),Bytes.toBytes("张三"));

            Put put2 = new Put(Bytes.toBytes("t001"),2);
            put2.addColumn(Bytes.toBytes(info),Bytes.toBytes("age"),Bytes.toBytes(20));

            Put put3 = new Put(Bytes.toBytes("t002"),3);
            put3.addColumn(Bytes.toBytes(info),Bytes.toBytes("city"),Bytes.toBytes("北京"));

            puts.add(put1);
            puts.add(put2);
            puts.add(put3);

            t2.put(puts);
        }*/


        List<Put> puts = new ArrayList<Put>();
        Put put1 = new Put(Bytes.toBytes("t003"),1);
        put1.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("张三"));

        Put put2 = new Put(Bytes.toBytes("t004"),2);
        put2.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"),Bytes.toBytes(20));

        Put put3 = new Put(Bytes.toBytes("t005"),3);
        put3.addColumn(Bytes.toBytes("info"),Bytes.toBytes("city"),Bytes.toBytes("北京"));

        puts.add(put1);
        puts.add(put2);
        puts.add(put3);

        t2.put(puts);
        t2.close();
        connection.close();

    }
    @Test
    public void testGet() throws IOException {
        //1.读取配置文件，先读取默认的配置文件，在读取自定义的配置文件
        Configuration conf = HBaseConfiguration.create();
        //2.建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        HTable t2 = (HTable) connection.getTable(TableName.valueOf("t3"));
        List<Get> gets = new ArrayList<>();
        Get get1 = new Get(Bytes.toBytes("t001"));
        Get get2 = new Get(Bytes.toBytes("t002"));
        Get get3 = new Get(Bytes.toBytes("t003"));

        gets.add(get1);
        gets.add(get2);
        gets.add(get3);

        Result[] results = t2.get(gets);
        Put put = new Put(Bytes.toBytes("t001"));
        for (Result r : results){
            Cell[] cells = r.rawCells();
            for(Cell cell : cells){

                put.add(cell);
                NavigableMap<byte[], List<Cell>> familyCellMap = put.getFamilyCellMap();
                System.out.println(familyCellMap.size());

                String cloumnName = Bytes.toString(CellUtil.cloneQualifier(cell));
                String cloumnValue ;
                if(cloumnName.equals("age")){
                    int a = Bytes.toInt(CellUtil.cloneValue(cell));
                    cloumnValue = a+"";
                }else {
                    cloumnValue = Bytes.toString(CellUtil.cloneValue(cell));
                }

                System.out.println(Bytes.toString(CellUtil.cloneRow(cell))+","+
                        Bytes.toString(CellUtil.cloneFamily(cell))+","+
                        Bytes.toString(CellUtil.cloneQualifier(cell))+":"+ cloumnValue);
            }
        }



    }

}
