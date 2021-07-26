package com.exclouds.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component
@Repository
@Mapper
public interface ExcloudsMapper {

  List<Map<String, String>> P_GetPTLogin(Map map);

  List<Map<String, String>> P_GetBaseInfo(Map map);

  List<Map<String, String>> P_GetHDInfo(Map map);

}

