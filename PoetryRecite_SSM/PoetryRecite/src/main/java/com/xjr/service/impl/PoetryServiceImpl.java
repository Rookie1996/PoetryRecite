package com.xjr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjr.dao.PoetryMapper;
import com.xjr.model.Poetry;
import com.xjr.model.PoetryExample;
import com.xjr.model.PoetryExample.Criteria;
import com.xjr.service.IPoetryService;

@Service("poetryService")
public class PoetryServiceImpl implements IPoetryService{
	
	@Autowired
	private PoetryMapper poetryMapper;

	@Override
	public List<Poetry> getAll() {
		// TODO Auto-generated method stub
		return poetryMapper.selectAll();
	}

	@Override
	public void savePoetry(Poetry poetry) {
		// TODO Auto-generated method stub
		poetry.setPoetryid(0);
		poetryMapper.insert(poetry);
	}

	@Override
	public Poetry getPoetry(Integer id) {
		// TODO Auto-generated method stub
		Poetry poetry = poetryMapper.selectByPrimaryKey(id);
		return poetry;
	}

	@Override
	public void updatePoetry(Poetry poetry) {
		// TODO Auto-generated method stub
		poetryMapper.updateByPrimaryKeySelective(poetry);
	}

	@Override
	public void deleteBatch(List<Integer> del_ids) {
		// TODO Auto-generated method stub
		PoetryExample p = new PoetryExample();
		Criteria c = p.createCriteria();
		c.andPoetryidIn(del_ids);
		poetryMapper.deleteByExample(p);
	}

	@Override
	public void deletePoetryById(Integer id) {
		// TODO Auto-generated method stub
		poetryMapper.deleteByPrimaryKey(id);
		
	}
	
	

}
