package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {

	int create(Menu menu);

	int delete(Integer id);

	int update(Menu menu);

	int updateSelective(Menu menu);

	List<Menu> query(Menu menu);

	Menu detail(Integer id);

	int count(Menu menu);

}
