package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@SpringBootApplication
@RestController
@EnableCaching // キャッシュ機能を有効にします
public class HelloRedisApplication {
    @Autowired
    Greeter greeter;

    @RequestMapping("/")
    String hello() {
        return greeter.hello() + " (" + System.getenv("CF_INSTANCE_INDEX") + ")";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloRedisApplication.class, args);
    }
}

@Component
class Greeter {
    @Cacheable("hello") // 実行結果をキャッシュします
    public String hello() {
        return "Hello. It's " + OffsetDateTime.now() + " now.";
    }
}
