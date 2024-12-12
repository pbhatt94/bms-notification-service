package com.wg.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.wg.banking.controller.PingController;


@SpringBootApplication
@Import({ PingController.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public ApplicationRunner runner(Publisher publisher) {
//        return args -> {
////            Thread.sleep(3000);
//            for (int i = 0; i < 10; i++) {
//                publisher.publishMessage(String.valueOf(i), "hello worldd", "abcd@gmail.com");
//            }
//        };
//    }

}