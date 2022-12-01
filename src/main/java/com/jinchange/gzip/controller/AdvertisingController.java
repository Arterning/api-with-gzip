package com.jinchange.gzip.controller;

import com.jinchange.gzip.entity.Advertising;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ProjectController
 * @Author zhangjin
 * @Date 2022/3/24 20:41
 * @Description:
 */
@Slf4j
@RestController
public class AdvertisingController {

    @PostMapping("/save")
    public Advertising saveProject(@RequestBody Advertising advertising) {
        //filter已经完成解压工作 这里直接拿到了字符串
        log.info("获取内容"+ advertising);
        return advertising;
    }
}
