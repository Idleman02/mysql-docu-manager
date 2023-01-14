package com.sjz.cloudcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**

 */
@SpringBootApplication
@EnableCaching
public class JavaBookManager {

    public static void main(String[] args) {
        SpringApplication.run(JavaBookManager.class, args);
    }

}
