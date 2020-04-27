package com.xjr.dao;

import com.xjr.model.Testype;
import com.xjr.model.TestypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestypeMapper {
    long countByExample(TestypeExample example);

    int deleteByExample(TestypeExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(Testype record);

    int insertSelective(Testype record);

    List<Testype> selectByExample(TestypeExample example);

    Testype selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") Testype record, @Param("example") TestypeExample example);

    int updateByExample(@Param("record") Testype record, @Param("example") TestypeExample example);

    int updateByPrimaryKeySelective(Testype record);

    int updateByPrimaryKey(Testype record);
}