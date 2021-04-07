package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.UserMenu;

public interface UserMenuMapper {

	public int create(UserMenu userMenu);

	public int delete(Integer id);

	public int update(UserMenu userMenu);

	public int updateSelective(UserMenu userMenu);

	public List<UserMenu> query(UserMenu userMenu);

	public UserMenu detail(Integer id);

	public int count(UserMenu userMenu);

}
