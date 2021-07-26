package com.exclouds.service;
import com.alibaba.fastjson.JSONObject;

public interface IOperationService {
  /**
   * 新增委托&更新委托&查询委托
   * @param billJson
   * @return
   */
  JSONObject operation(JSONObject billJson);
  JSONObject query(JSONObject billJson);

}
