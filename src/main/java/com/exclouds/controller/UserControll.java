package com.exclouds.controller;

import com.alibaba.fastjson.JSONObject;
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
public class UserControll {

  @Autowired
  private IUserService userService;

  /***
   * 登录校验&修改密码
   * @param userJson
   * @return
   */
  @PostMapping("/login")
  @ResponseBody
  public JSONObject login(@RequestBody JSONObject userJson) {
    return userService.login(userJson);
  }

}

