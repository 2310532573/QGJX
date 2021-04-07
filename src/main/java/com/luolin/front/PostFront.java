package com.luolin.front;

import com.github.pagehelper.PageInfo;
import com.luolin.service.PostService;
import com.luolin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/f/post/")
public class PostFront {

    @Autowired
    PostService postService;

    @PostMapping("query")
    public Map<String,Object> getPostList(@RequestBody HashMap map){
        PageInfo<HashMap> postList = postService.getPostList(map);
        return Result.ok(postList);
    }

}
