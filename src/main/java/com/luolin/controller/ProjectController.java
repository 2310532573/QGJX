package com.luolin.controller;

import com.github.pagehelper.PageInfo;
import com.luolin.entity.Project;
import com.luolin.entity.Resume;
import com.luolin.service.ProjectService;
import com.luolin.service.ResumeService;
import com.luolin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ResumeService resumeService;

    @PostMapping("create")
    public Result create(@RequestBody Project project){
        int flag = projectService.create(project);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = projectService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Project project){
        int flag = projectService.update(project);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(projectService.detail(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Project project){
        PageInfo<Project> pageInfo = projectService.query(project);
        pageInfo.getList().forEach(item->{
            Resume detail = resumeService.detail(item.getResumeId());
            item.setResume(detail);
        });
        return Result.ok(pageInfo);
    }

}
