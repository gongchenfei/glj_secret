package com.exclouds.controller;

import com.alibaba.fastjson.JSONObject;
import com.exclouds.service.IBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequestMapping
public class BaseInfoController {
  @Autowired
  private IBaseInfoService BaseInfo;

  /***
   * 获取基础信息
   * @param baseJson
   * @return
   */
  @PostMapping("/baseinfo")
  @ResponseBody
  public JSONObject baseinfo(@RequestBody JSONObject baseJson) {
    return BaseInfo.baseinfo(baseJson);
  }
}
