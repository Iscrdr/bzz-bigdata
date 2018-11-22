package com.bzz.cloud.hbase.mapreducer;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TestHbaseMapper extends TableMapper<ImmutableBytesWritable, Put> {

    protected void map(ImmutableBytesWritable key, Result value,
                       Mapper<ImmutableBytesWritable, Result, ImmutableBytesWritable, Put>.Context context)
            throws IOException, InterruptedException {

        //封装put对象
        Put put=new Put(key.get());

        //遍历结果集
        for(Cell cell:value.rawCells()) {

            // 先判断列簇是否是info

            if("info".equals(Bytes.toString(CellUtil.cloneFamily(cell)))) {
                //在判断列是否是name
                if("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                    put.add(cell);
                }
            }
        }

        context.write(key, put);
    }


}
