package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Intention;

public interface IntentionMapper {

	public int create(Intention intention);

	public int delete(Integer id);

	public int update(Intention intention);

	public int updateSelective(Intention intention);

	public List<Intention> query(Intention intention);

	public Intention detail(Integer id);

	public int count(Intention intention);

}
