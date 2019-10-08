package com.sun.rabbit;

import com.sun.rabbit.producer.AckProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 喻湘东
 * @Create 2019-10-08 09:44:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AckTest {

    @Autowired
    private AckProducer ackProducer;

    @Test
    public void send() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            ackProducer.send("123___"+i);
        }

        Thread.sleep(10000);
    }

}
