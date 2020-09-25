package com.xs.bqx.orderprovider.dao;

import com.xs.bqx.orderprovider.pojo.RabbitMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OderMapper {


    List<Map> selectWxUserInfo();


    void addmess(RabbitMessage re);
}
