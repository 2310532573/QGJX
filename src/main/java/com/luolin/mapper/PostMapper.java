package com.luolin.mapper;

import java.util.HashMap;
import java.util.List;

import com.luolin.entity.Post;

public interface PostMapper {

	public int create(Post post);

	public int delete(Integer id);

	public int update(Post post);

	public int updateSelective(Post post);

	public List<Post> query(Post post);

	public Post detail(Integer id);

	public int count(Post post);

	public List<HashMap> getPostList(HashMap map);
}
