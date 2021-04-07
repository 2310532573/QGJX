package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Profession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfessionMapper {

	int create(Profession profession);

	int delete(Integer id);

	int update(Profession profession);

	int updateSelective(Profession profession);

	List<Profession> query(Profession profession);

	Profession detail(Integer id);

	int count(Profession profession);

}
