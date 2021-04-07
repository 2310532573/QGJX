package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Profession;

public interface ProfessionMapper {

	public int create(Profession profession);

	public int delete(Integer id);

	public int update(Profession profession);

	public int updateSelective(Profession profession);

	public List<Profession> query(Profession profession);

	public Profession detail(Integer id);

	public int count(Profession profession);

}
