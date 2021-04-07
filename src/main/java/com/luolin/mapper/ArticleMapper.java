package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    int create(Article article);

    int delete(Integer id);

    int update(Article article);

    int updateSelective(Article article);

    List<Article> query(Article article);

    List<Article> getArticlesByChannelId(Integer channelId);

    Article detail(Integer id);

    int count(Article article);

}
