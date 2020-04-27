package com.xjr.dao;

import com.xjr.model.SumGradeView;
import com.xjr.model.SumGradeViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SumGradeViewMapper {
    long countByExample(SumGradeViewExample example);

    int deleteByExample(SumGradeViewExample example);

    int insert(SumGradeView record);

    int insertSelective(SumGradeView record);

    List<SumGradeView> selectByExample(SumGradeViewExample example);

    int updateByExampleSelective(@Param("record") SumGradeView record, @Param("example") SumGradeViewExample example);

    int updateByExample(@Param("record") SumGradeView record, @Param("example") SumGradeViewExample example);

    /**
     * 获得所有用户的总成绩
     * @return
     */
	List<SumGradeView> getAll();
}