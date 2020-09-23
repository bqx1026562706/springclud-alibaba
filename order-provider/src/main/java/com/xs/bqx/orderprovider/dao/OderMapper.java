package com.xs.bqx.orderprovider.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OderMapper {


    List<Map> selectWxUserInfo();
}
