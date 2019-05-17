package com.sun.quarzt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class,args);
    }

    // */10 * * * * ?每10秒执行一次
    @Scheduled(cron="*/10 * * * * ?")
    public void test(){
        System.out.println("任务执行了");
    }

}
