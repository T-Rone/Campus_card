package com.dragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tdragon.
 * @Date 2021/5/30.
 * @Time 17:28
 * @Description:
 */
@CrossOrigin(origins = {"http://localhost:8080", "null"})
@SpringBootApplication
public class CampusCardApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusCardApplication.class, args);
    }
}
