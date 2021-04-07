package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Menu;

public interface MenuMapper {

	public int create(Menu menu);

	public int delete(Integer id);

	public int update(Menu menu);

	public int updateSelective(Menu menu);

	public List<Menu> query(Menu menu);

	public Menu detail(Integer id);

	public int count(Menu menu);

}
