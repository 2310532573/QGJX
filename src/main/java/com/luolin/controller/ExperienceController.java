package com.luolin.controller;

import com.github.pagehelper.PageInfo;
import com.luolin.entity.Experience;
import com.luolin.entity.Resume;
import com.luolin.service.ExperienceService;
import com.luolin.service.ResumeService;
import com.luolin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private ResumeService resumeService;

    @PostMapping("create")
    public Result create(@RequestBody Experience experience){
        int flag = experienceService.create(experience);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = experienceService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Experience experience){
        int flag = experienceService.update(experience);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(experienceService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Experience experience){
        PageInfo<Experience> pageInfo = experienceService.query(experience);
        pageInfo.getList().forEach(item->{
            Resume detail = resumeService.detail(item.getResumeId());
            item.setResume(detail);
        });
        return Result.ok(pageInfo);
    }

}
