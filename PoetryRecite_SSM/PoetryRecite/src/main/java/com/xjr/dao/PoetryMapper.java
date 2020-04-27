package com.xjr.dao;

import com.xjr.model.Poetry;
import com.xjr.model.PoetryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoetryMapper {
    long countByExample(PoetryExample example);

    int deleteByExample(PoetryExample example);

    int deleteByPrimaryKey(Integer poetryid);

    int insert(Poetry record);

    int insertSelective(Poetry record);

    List<Poetry> selectByExample(PoetryExample example);

    Poetry selectByPrimaryKey(Integer poetryid);

    int updateByExampleSelective(@Param("record") Poetry record, @Param("example") PoetryExample example);

    int updateByExample(@Param("record") Poetry record, @Param("example") PoetryExample example);

    int updateByPrimaryKeySelective(Poetry record);

    int updateByPrimaryKey(Poetry record);
    
    //查询所有诗歌
	List<Poetry> selectAll();
}