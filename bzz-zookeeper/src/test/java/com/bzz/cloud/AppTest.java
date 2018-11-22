package com.bzz.cloud;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Unit test for simple App.
 */
public class AppTest {
    private Logger logger = LoggerFactory.getLogger(AppTest.class);
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(){
        logger.info("123"+123);
        logger.info("hello");
        logger.error("错误信息");
        System.out.println("===========");
        System.out.println("===========");
        System.out.println("===========");
        int a = 5/0;
	    System.out.println("错误信息");
    }
}
