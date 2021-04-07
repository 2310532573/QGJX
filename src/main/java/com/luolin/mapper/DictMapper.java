package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Dict;

public interface DictMapper {

	public int create(Dict dict);

	public int delete(Integer id);

	public int update(Dict dict);

	public int updateSelective(Dict dict);

	public List<Dict> query(Dict dict);

	public Dict detail(Integer id);

	public int count(Dict dict);

}
