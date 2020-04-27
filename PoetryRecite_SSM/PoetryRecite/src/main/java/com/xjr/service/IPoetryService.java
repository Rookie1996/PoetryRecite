package com.xjr.service;

import java.util.List;

import com.xjr.model.Poetry;

public interface IPoetryService {

	//查询所有诗歌
	List<Poetry> getAll();

	//保存新增诗歌
	void savePoetry(Poetry poetry);

	//按id查询诗歌
	Poetry getPoetry(Integer id);

	//修改诗歌
	void updatePoetry(Poetry poetry);

	/**
	 * 批量删除诗歌
	 * @param del_ids
	 */
	void deleteBatch(List<Integer> del_ids);
	
	/**
	 * 按诗歌编号单个删除
	 * @param id
	 */
	void deletePoetryById(Integer id);

}
