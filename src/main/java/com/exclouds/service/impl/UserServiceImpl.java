package com.exclouds.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.exclouds.dao.ExcloudsMapper;
import com.exclouds.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements IUserService{

  @Autowired private ExcloudsMapper ExcloudsMapper;

  /***
   * 登录&修改密码&查询用户信息
   * @param userJson
   * @return
   */
  @Override
  public JSONObject login(JSONObject userJson) {

    HashMap<String, String> map = new HashMap<String, String>();
    List<Map<String, String>> resultmaplist = new ArrayList<>();
    JSONObject jsonresult = new JSONObject();
    String success;
    try{
      map.put("user",userJson.getString("user"));
      map.put("password",userJson.getString("password"));
      map.put("flag",userJson.getString("flag"));
      if (userJson.containsKey("xpassword")) {
        map.put("xpassword", userJson.getString("xpassword"));
      }
      resultmaplist = ExcloudsMapper.P_GetPTLogin(map);
      success = resultmaplist.get(0).get("success");
      jsonresult.put("success", success);
      jsonresult.put("msginfo", resultmaplist.get(0).get("msginfo"));
      if (success.equals("Y")) {
        jsonresult.put("user", resultmaplist.get(0).get("usr"));
        jsonresult.put("name", resultmaplist.get(0).get("name"));
        jsonresult.put("cusno", resultmaplist.get(0).get("cus_no"));
      }

      return jsonresult;
    } catch (Exception e) {
      jsonresult.put("success","N");
      jsonresult.put("msginfo","登录错误，error=="+e.getLocalizedMessage());
      return jsonresult;
    }finally {

    }
  }
}
