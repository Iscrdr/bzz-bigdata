package com.bzz.cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Hello world!
 *
 */
public class App {
    static void testAtomicLongVSLongAdder(final int threadCount, final int times) {
        try {
            long start = System.currentTimeMillis();
            testLongAdder(threadCount, times);
            long end = System.currentTimeMillis() - start;
            System.out.println("线程数:" + threadCount + ", 单线程操作计数" + times+"，结果：LongAdder方式增加计数" + (threadCount * times)/10000 + "万次,共计耗时:" + end);
           
            long start2 = System.currentTimeMillis();
            testAtomicLong(threadCount, times);
            long end2 = System.currentTimeMillis() - start2;
            System.out.println("线程数:" + threadCount + ", 单线程操作计数" + times+"，结果：AtomicLong方式增加计数" + (threadCount * times)/10000 + "万次,共计耗时:" + end2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    static void testAtomicLong(final int threadCount, final int times) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        final AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < times; j++) {
                        atomicLong.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }, "my-thread" + i));
        }
        for (Thread thread : list) {
            thread.start();
        }
        countDownLatch.await();
    }
    
    static void testLongAdder(final int threadCount, final int times) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        final LongAdder longAdder = new LongAdder();
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < times; j++) {
                        longAdder.add(1);
                    }
                    countDownLatch.countDown();
                }
            }, "my-thread" + i));
        }
        for (Thread thread : list) {
            thread.start();
        }
        countDownLatch.await();
    }

    public static void main( String[] args ) {
        testAtomicLongVSLongAdder(100, 1000000);
        //testAtomicLongVSLongAdder(20, 50000);
        //testAtomicLongVSLongAdder(40, 200000);
        //testAtomicLongVSLongAdder(100, 2000000);
    
    }
}
