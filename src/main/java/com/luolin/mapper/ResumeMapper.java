package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Resume;

public interface ResumeMapper {

	public int create(Resume resume);

	public int delete(Integer id);

	public int update(Resume resume);

	public int updateSelective(Resume resume);

	public List<Resume> query(Resume resume);

	public Resume detail(Integer id);

	public int count(Resume resume);

}
