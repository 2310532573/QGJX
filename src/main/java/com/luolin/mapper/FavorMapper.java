package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Favor;

public interface FavorMapper {

	public int create(Favor favor);

	public int delete(Integer id);

	public int update(Favor favor);

	public int updateSelective(Favor favor);

	public List<Favor> query(Favor favor);

	public Favor detail(Integer id);

	public int count(Favor favor);

}
