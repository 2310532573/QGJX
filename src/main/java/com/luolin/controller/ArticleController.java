package com.luolin.controller;

import com.github.pagehelper.PageInfo;
import com.luolin.common.annotation.LogAnnotation;
import com.luolin.entity.Article;
import com.luolin.common.redis.RedisUtil;
import com.luolin.service.ArticleService;
import com.luolin.utils.Result;
import com.luolin.utils.UserThreadLocal;
import com.luolin.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("create")
    @LogAnnotation
    public Result create(@RequestBody Article article){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        article.setCreateDate(new Date());
        article.setCreateUser(userData.getId());
        int flag = articleService.create(article);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    @LogAnnotation
    public Result delete(String ids){
        int flag = articleService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    @LogAnnotation
    public Result update(@RequestBody Article article){
        int flag = articleService.update(article);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    @LogAnnotation
    public Result detail(Integer id){
        return  Result.ok(articleService.detail(id));
    }

    @PostMapping("query")
    @LogAnnotation
    public Map<String,Object> query(@RequestBody  Article article){
        PageInfo<Article> pageInfo = articleService.query(article);
        return Result.ok(pageInfo);
    }

}
