package com.luolin.entity;

import com.luolin.utils.Entity;

public class UserMenu extends Entity{

	/**
	 *
	 */
	private Integer userId;
	/**
	 *
	 */
	private Integer menuId;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
