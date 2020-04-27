package com.xjr.dao;

import com.xjr.model.Test;
import com.xjr.model.TestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestMapper {
    long countByExample(TestExample example);

    int deleteByExample(TestExample example);

    int deleteByPrimaryKey(Integer testid);

    int insert(Test record);

    int insertSelective(Test record);

    List<Test> selectByExample(TestExample example);

    Test selectByPrimaryKey(Integer testid);

    int updateByExampleSelective(@Param("record") Test record, @Param("example") TestExample example);

    int updateByExample(@Param("record") Test record, @Param("example") TestExample example);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
    
    //查询所有试题
	List<Test> selectAll();
	
	//按试题类型查询试题
	List<Test> selectTestByTypeId(Integer typeId);

	//模糊查询试题
	List<Test> selectByContentAndType(String testContent, int typeId);

	//查询所有试题内容
	List<String> getTestContent();
}