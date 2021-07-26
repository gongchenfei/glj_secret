package com.exclouds.service;
import com.alibaba.fastjson.JSONObject;

public interface IBaseInfoService {

  /***
   * 基础信息获取
   * @param baseJson
   * @return
   */
  JSONObject  baseinfo(JSONObject baseJson);

}
