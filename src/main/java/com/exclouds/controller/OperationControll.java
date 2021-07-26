package com.exclouds.controller;

import com.alibaba.fastjson.JSONObject;
import com.exclouds.service.IOperationService;
import com.exclouds.service.IUserService;
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

public class OperationControll {
  @Autowired
  private IOperationService operationService;

  /**
   * 新增委托&更新委托
   * @param billJson
   * @return
   */
  @PostMapping("/operation")
  @ResponseBody
  public JSONObject operation(@RequestBody JSONObject billJson){
    return operationService.operation(billJson);
  }

  /**
   * 查询委托
   * @param billJson
   * @return
   */
  @PostMapping("/query")
  @ResponseBody
  public JSONObject query(@RequestBody JSONObject billJson){
    return operationService.query(billJson);
  }

}
