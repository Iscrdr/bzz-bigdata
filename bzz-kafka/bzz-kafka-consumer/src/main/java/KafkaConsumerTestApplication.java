import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;


/**
 * @description：springboot 整合 kafka
 * @author     ：Iscrdr
 * @date       ：2020/01/12 17:47
 * @email      ：624003618@qq.com
 * @modified By：
 * @version:     1.0.0
 */
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.bzz.cloud"})
public class KafkaConsumerTestApplication implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(KafkaConsumerTestApplication.class);

    public static void main( String[] args ) {
        SpringApplication.run(KafkaConsumerTestApplication.class,args).close();
    }


    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "topic-test")
    public void listen(ConsumerRecord<String, String> cr) throws Exception {
        logger.info("topic:{},partition:{},offset:{},timestamp:{},key:{},value:{}",
                cr.topic(),cr.partition(),cr.offset(),cr.timestamp(),cr.key(),cr.value());
       // latch.countDown();
    }

    @Override
    public void run(String... args) throws Exception {
        latch.await();
    }
}
