package com.jinchange.gzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = {"com.jinchange.gzip.filter"})
@SpringBootApplication
public class GzipApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzipApplication.class, args);
    }

}
