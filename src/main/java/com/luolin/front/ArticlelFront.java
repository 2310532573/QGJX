package com.luolin.front;

import com.github.pagehelper.PageInfo;
import com.luolin.entity.Article;
import com.luolin.service.ArticleService;
import com.luolin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/f/article/")
public class ArticlelFront {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("detail")
    public Result detail(Integer id){
        Double views = redisTemplate.opsForZSet().incrementScore("views", id, 1);
        Article detail = articleService.detail(id);
        if(views != null){
            detail.setViews(views.intValue());
        }else{
            detail.setViews(0);
        }
        return  Result.ok(detail);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody Article article){
        PageInfo<Article> pageInfo = articleService.getArticlesByChannelId(article);
        return Result.ok(pageInfo);
    }


    @GetMapping("hot")
    public Result hot(){
        List<Article> list = new ArrayList<>();
        Set set = redisTemplate.opsForZSet().reverseRangeByScore("views", 1, Double.MAX_VALUE);
        int i = 0;
        for (Object o : set) {
            if(i<10){
                Article article = articleService.detail(Integer.parseInt(o+""));
                list.add(article);
            }else{
                break;
            }
            i++;
        }
        return Result.ok(list);
    }

}
