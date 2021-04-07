package com.luolin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luolin.entity.Article;
import com.luolin.mapper.ArticleMapper;
import com.luolin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int create(Article article) {
        return articleMapper.create(article);
    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                articleMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    @Override
    public int delete(Integer id) {
        return articleMapper.delete(id);
    }

    @Override
    public int update(Article article) {
        return articleMapper.update(article);
    }

    @Override
    public int updateSelective(Article article) {
        return articleMapper.updateSelective(article);
    }

    @Override
    public PageInfo<Article> query(Article article) {
        if(article != null && article.getPage() != null){
            PageHelper.startPage(article.getPage(),article.getLimit());
        }
        return new PageInfo<Article>(articleMapper.query(article));
    }

    @Override
    public Article detail(Integer id) {
        return articleMapper.detail(id);
    }

    @Override
    public int count(Article article) {
        return articleMapper.count(article);
    }

    @Override
    public PageInfo<Article> getArticlesByChannelId(Article article){
        if(article != null && article.getPage() != null){
            PageHelper.startPage(article.getPage(),article.getLimit());
        }
        return new PageInfo<Article>(articleMapper.getArticlesByChannelId(article.getChannelId()));
    }

}
