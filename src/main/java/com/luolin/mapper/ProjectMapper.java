package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Project;

public interface ProjectMapper {

	public int create(Project project);

	public int delete(Integer id);

	public int update(Project project);

	public int updateSelective(Project project);

	public List<Project> query(Project project);

	public Project detail(Integer id);

	public int count(Project project);

}
