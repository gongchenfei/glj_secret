
package com.exclouds.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exclouds.service.IBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BaseInfoServiceImpl implements IBaseInfoService {

  @Autowired
  private com.exclouds.dao.ExcloudsMapper ExcloudsMapper;

  /***
   * 获取基础信息
   * @param baseJson
   * @return
   */
  @Override
  public JSONObject baseinfo(JSONObject baseJson) {
    HashMap<String, String> map = new HashMap<String, String>();
    List<Map<String, String>> resultmaplist = new ArrayList<>();
    JSONObject jsonresult = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    try{
      map.put("type",baseJson.getString("type"));

      resultmaplist = ExcloudsMapper.P_GetBaseInfo(map);

      if (resultmaplist.size() <= 0) {
        jsonresult.put("success", "N");
        jsonresult.put("msginfo", "未查询到数据");
      } else {
        for (int j = 0; j < resultmaplist.size(); j++){
          Map<String, String> resultmap = resultmaplist.get(j);
          JSONObject tmpJson = new JSONObject();
          tmpJson.put("value",resultmap.get("data"));
          jsonArray.add(tmpJson);
        }
        jsonresult.put("success", "Y");
        jsonresult.put("msginfo", "成功");
        jsonresult.put("data", jsonArray);
      }
      return jsonresult;
    } catch (Exception e) {
      jsonresult.put("success","N");
      jsonresult.put("msginfo","查询基础信息错误，error=="+e.getLocalizedMessage());
      return jsonresult;
    }finally {

    }
  }
}
