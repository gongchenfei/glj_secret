package com.exclouds.service;
import com.alibaba.fastjson.JSONObject;

public interface IUserService {

  /***
   * 登录校验&修改密码
   * @param userJson
   * @return
   */
  JSONObject login(JSONObject userJson);

}
