package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {

	int create(Company company);

	int delete(Integer id);

	int update(Company company);

	int updateSelective(Company company);

	List<Company> query(Company company);

	Company detail(Integer id);

	int count(Company company);

}
