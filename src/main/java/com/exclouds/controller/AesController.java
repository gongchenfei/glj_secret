package com.exclouds.controller;

import com.exclouds.service.IAesEncodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/glj/aes")
public class AesController {

    @Autowired
    private IAesEncodeService aesService;

    @PostMapping("/encode")
    @ResponseBody
    public String getBonusActivityList(@RequestBody String encodeStr) {
        return aesService.encrypt(encodeStr);
    }
}
