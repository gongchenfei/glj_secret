package com.exclouds.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exclouds.dao.ExcloudsMapper;
import com.exclouds.service.IOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service

public class IOperationServiceImpl implements IOperationService {

  @Autowired private ExcloudsMapper ExcloudsMapper;

  /**
   * 新增委托
   * @param billJson
   * {"type":"update","data":{"billNo":"WTHD20220107001","pod":"CAVAN","shipname":"MAERSK HOUSTON","ovoy":"139W","carrier":"MSC",
   * "agent":"MSC","ctnSize":"20","ctnType":"GP","qty":2,"remark":"","creator":"SA"}}
   * @return
   */
  @Override
  public JSONObject operation(JSONObject billJson){
    JSONObject jsonresult = new JSONObject();
    JSONObject jsonData = new JSONObject();
    HashMap<String, String> map = new HashMap<String, String>();
    List<Map<String, String>> resultmaplist = new ArrayList<>();
    JSONArray jsonArray = new JSONArray();
    String success;
    try{
      //判断必填项
      jsonData = billJson.getJSONObject("data");
      if (!jsonData.containsKey("billNo")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","提单号不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("pod")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","目的港不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("shipname")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","船名不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("ovoy")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","航次不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("carrier")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","船公司不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("agent")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","货代不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("ctnSize")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","尺寸不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("ctnType")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","箱型不可为空");
        return jsonresult;
      }
      if (!jsonData.containsKey("qty")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","数量不可为空");
        return jsonresult;
      }
      map.put("type",billJson.getString("type"));
      map.put("data",billJson.getJSONObject("data").toString());
      resultmaplist = ExcloudsMapper.P_GetHDInfo(map);
      success = resultmaplist.get(0).get("success");
      jsonresult.put("success", success);
      jsonresult.put("msginfo", resultmaplist.get(0).get("msginfo"));
      if (billJson.getString("type").equals("query")) {
        for (int j = 0; j < resultmaplist.size(); j++){
          Map<String, String> resultmap = resultmaplist.get(j);
          JSONObject tmpJson = new JSONObject();
          //billNo,pod,shipname,ovoy,carrier,agent,ctnSize,ctnType,qty,remark,state,user,createDate,updateDate
          tmpJson.put("billNo",resultmap.get("billNo"));
          tmpJson.put("pod",resultmap.get("pod"));
          tmpJson.put("shipname",resultmap.get("shipname"));
          tmpJson.put("ovoy",resultmap.get("ovoy"));
          tmpJson.put("carrier",resultmap.get("carrier"));
          tmpJson.put("agent",resultmap.get("agent"));
          tmpJson.put("ctnSize",resultmap.get("ctnSize"));
          tmpJson.put("ctnType",resultmap.get("ctnType"));
          tmpJson.put("qty",resultmap.get("qty"));
          tmpJson.put("remark",resultmap.get("remark"));
          tmpJson.put("user",resultmap.get("user"));
          jsonArray.add(tmpJson);
        }
        jsonresult.put("data",jsonArray);
      }
      return jsonresult;
    } catch (Exception e) {
      jsonresult.put("success","N");
      jsonresult.put("msginfo","委托操作错误，error=="+e.getLocalizedMessage());
      return jsonresult;
    }finally {

    }

  }

  /**
   * 查询委托
   * @param billJson
   * {"type":"add","data":{"TD_NO":"1112A","END_PORT":"QINGTAO","ENAME":"SANTA LORETTA","OVOY":"ZMM8E","CUS_NO_S":"MSC","CUS_NO_H":"8888","CC":"20","XX":"GP","QTY":1,"REMARK":""}}
   * {"type":"query","data":{"TD_NO":"1112A","ENAME":"SANTA LORETTA","OVOY":"ZMM8E","CUS_NO_S":"MSC","CUS_NO_H":"8888","SH_FLAG":"W"}
   * @return
   */
  @Override
  public JSONObject query(JSONObject billJson){
    JSONObject jsonresult = new JSONObject();
    JSONObject jsonData = new JSONObject();
    HashMap<String, String> map = new HashMap<String, String>();
    List<Map<String, String>> resultmaplist = new ArrayList<>();
    JSONArray jsonArray = new JSONArray();
    String success;
    try{
      //判断必填项
      jsonData = billJson.getJSONObject("data");
      if (!jsonData.containsKey("agent")) {
        jsonresult.put("success","N");
        jsonresult.put("msginfo","货代不可为空");
        return jsonresult;
      }
      map.put("type",billJson.getString("type"));
      map.put("data",billJson.getJSONObject("data").toString());
      resultmaplist = ExcloudsMapper.P_GetHDInfo(map);
      success = resultmaplist.get(0).get("success");
      jsonresult.put("success", success);
      jsonresult.put("msginfo", resultmaplist.get(0).get("msginfo"));
      if (billJson.getString("type").equals("query")) {
        for (int j = 0; j < resultmaplist.size(); j++){
          Map<String, String> resultmap = resultmaplist.get(j);
          JSONObject tmpJson = new JSONObject();
          //billNo,pod,shipname,ovoy,carrier,agent,ctnSize,ctnType,qty,remark,state,creator,createDate,updateDate
          tmpJson.put("billNo",resultmap.get("billNo"));
          tmpJson.put("pod",resultmap.get("pod"));
          tmpJson.put("shipname",resultmap.get("shipname"));
          tmpJson.put("ovoy",resultmap.get("ovoy"));
          tmpJson.put("carrier",resultmap.get("carrier"));
          tmpJson.put("agent",resultmap.get("agent"));
          tmpJson.put("ctnSize",resultmap.get("ctnSize"));
          tmpJson.put("ctnType",resultmap.get("ctnType"));
          tmpJson.put("qty",resultmap.get("qty"));
          tmpJson.put("state",resultmap.get("state"));
          tmpJson.put("state",resultmap.get("creator"));
          tmpJson.put("createdate",resultmap.get("createdate"));
          jsonArray.add(tmpJson);
        }
        jsonresult.put("data",jsonArray);
      }
      return jsonresult;
    } catch (Exception e) {
      jsonresult.put("success","N");
      jsonresult.put("msginfo","委托操作错误，error=="+e.getLocalizedMessage());
      return jsonresult;
    }finally {

    }

  }
}

