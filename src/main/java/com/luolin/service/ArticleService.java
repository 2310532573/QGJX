package com.luolin.service;

import com.luolin.mapper.ArticleMapper;
import com.luolin.entity.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

public interface ArticleService {


    int create(Article article);

    int delete(String ids);

    int delete(Integer id);

    int update(Article article);

    int updateSelective(Article article);

    PageInfo<Article> query(Article article);

    Article detail(Integer id);

    int count(Article article);

    PageInfo<Article> getArticlesByChannelId(Article article);
}
