package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Article;

public interface ArticleMapper {

	public int create(Article article);

	public int delete(Integer id);

	public int update(Article article);

	public int updateSelective(Article article);

	public List<Article> query(Article article);

	public List<Article> getArticlesByChannelId(Integer channelId);

	public Article detail(Integer id);

	public int count(Article article);

}
