package com.bzz.common.database;

import com.bzz.common.database.dialect.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: bzz-cloud
 * @description:
 * @author: Yang qianli
 * @create: 2018-10-15 20:41
 * @version: 1.0.0
 */
public class DialectUtils {
	private static Map<String,String> dialectMap = new HashMap<String,String>();
	static {
		dialectMap.put("dataSourceA","mysql");
		dialectMap.put("dataSourceB","mysql");
		dialectMap.put("dataSourceC","oracle");
		dialectMap.put("dataSourceD","postgresql");
		dialectMap.put("dataSourceE","sqlServer");
	}
	public static String getDynamicDialect(String dialect){
		return dialectMap.get(dialect);
	}
}
