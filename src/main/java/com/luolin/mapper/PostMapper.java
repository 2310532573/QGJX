package com.luolin.mapper;

import java.util.HashMap;
import java.util.List;

import com.luolin.entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

	int create(Post post);

	int delete(Integer id);

	int update(Post post);

	int updateSelective(Post post);

	List<Post> query(Post post);

	Post detail(Integer id);

	int count(Post post);

	List<HashMap> getPostList(HashMap map);
}
