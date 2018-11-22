/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.bzz.common.database.dialect;


import com.bzz.common.database.DynamicDialect;

/**
 * @description: Mysql Dialect分页实现
 * @author: Yang qianli
 * @createTime: 2018-10-15 17:53:18
 * @updateTime: 2018-10-15 17:53:18
 * @version: 1.0.0
 */
public class MySQLDialect implements DynamicDialect {
    
    
    /**
     * 判断数据库是否支持分页，true：支持分页
     * @return
     */
    public boolean isSupportsLimit() {
        return true;
    }
    
    /**
     * @param sql    SQL语句
     * @param pageNo 开始条数
     * @param pageSize  每页显示多少纪录条数
     * @return
     */
    public String getPageSql(String sql, int pageNo, int pageSize) {
        StringBuilder sqlBuffer = new StringBuilder();
        sqlBuffer.append("select * from ( " + sql +" ) t");
        sqlBuffer.append(" where t.id > ( select id from t limit "+ pageNo+"," +" 1 ) limit " + pageNo+","+pageSize);
        return sqlBuffer.toString();
    }
}
