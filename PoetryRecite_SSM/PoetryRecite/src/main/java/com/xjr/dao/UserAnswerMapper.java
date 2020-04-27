package com.xjr.dao;

import com.xjr.model.UserAnswer;
import com.xjr.model.UserAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAnswerMapper {
    long countByExample(UserAnswerExample example);

    int deleteByExample(UserAnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAnswer record);

    int insertSelective(UserAnswer record);

    List<UserAnswer> selectByExample(UserAnswerExample example);

    UserAnswer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAnswer record, @Param("example") UserAnswerExample example);

    int updateByExample(@Param("record") UserAnswer record, @Param("example") UserAnswerExample example);

    int updateByPrimaryKeySelective(UserAnswer record);

    int updateByPrimaryKey(UserAnswer record);

    /**
     * 查询所有用户答题记录
     * @return
     */
	List<UserAnswer> selectAll();

	/**
	 * 查询答题记录
	 * @param userid
	 * @param testid
	 * @return
	 */
	UserAnswer selectByUseridAndTestid(Long userid, Integer testid);

	/**
	 * 更新答题记录
	 * @param userAnswer
	 */
	void update(UserAnswer userAnswer);

	/**
	 * 查询testid试题有没有被回答过
	 * @param parseInt
	 * @return
	 */
	Integer selectByTestid(int testid);

	/**
	 * 按userid查询所有useranswer记录
	 * @param userid
	 * @return
	 */
	List<UserAnswer> getUserAnswer(Long userid);
}