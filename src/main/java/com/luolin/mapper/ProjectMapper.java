package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {

	int create(Project project);

	int delete(Integer id);

	int update(Project project);

	int updateSelective(Project project);

	List<Project> query(Project project);

	Project detail(Integer id);

	int count(Project project);

}
