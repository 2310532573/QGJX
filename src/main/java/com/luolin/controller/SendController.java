package com.luolin.controller;

import com.github.pagehelper.PageInfo;
import com.luolin.common.annotation.LogAnnotation;
import com.luolin.entity.Resume;
import com.luolin.entity.Send;
import com.luolin.common.redis.RedisUtil;
import com.luolin.common.role.RequiresRoles;
import com.luolin.common.role.Role;
import com.luolin.service.*;
import com.luolin.utils.Result;
import com.luolin.utils.UserThreadLocal;
import com.luolin.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private SendService sendService;
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private PostService postService;

    @Autowired
    private StudentService studentService;

    @PostMapping("create")
    @LogAnnotation()
    public Result create(@RequestBody Map<String,Integer> map){
        UserData userData = userDataService.getUserData();
        //1、判断用户是否登录
        if(userData == null){
            return Result.ok("未登录不能操作");
        }
        Integer postId = map.get("postId");
        Integer companyId = map.get("companyId");

        //2、判断是否重复投递简历
        Send sendParam = new Send();
        sendParam.setStudentId(userData.getId());
        sendParam.setPostId(postId);
        sendParam.setCompanyId(companyId);
        PageInfo<Send> sendPageInfo = sendService.query(sendParam);
        if(sendPageInfo.getList() != null && sendPageInfo.getList().size()>0){
            return Result.fail("请勿重复投递简历");
        }

        Send send = new Send();
        send.setPostId(postId);
        send.setCompanyId(companyId);
        send.setSendDate(new Date());
        send.setStatus(0);
        send.setStudentId(userData.getId());

        //3、判断简历是否开放
        Resume param = new Resume();
        param.setStudentId(userData.getId());
        param.setStatus(1);
        PageInfo<Resume> pageInfo = resumeService.query(param);
        if(pageInfo.getList() != null && pageInfo.getList().size()>0){
            Resume entity = pageInfo.getList().get(0);
            send.setResumeId(entity.getId());
        }else{
            return Result.fail("请完善简历");
        }
        //4、投递
        sendService.create(send);
        return Result.ok();
    }

    @GetMapping("delete")
    @LogAnnotation()
    public Result delete(String ids){
        sendService.delete(ids);
        return Result.ok();
    }

    @PostMapping("update")
    @LogAnnotation()
    public Result update(@RequestBody Send send){
        sendService.update(send);
        return Result.ok();

    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(sendService.detail(id));
    }

    @RequiresRoles(type = Role.COMPANY)
    @PostMapping("query")
    @LogAnnotation()
    public Map<String,Object> query(@RequestBody  Send send){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        send.setCompanyId(userData.getId());
        PageInfo<Send> pageInfo = sendService.query(send);

        pageInfo.getList().forEach(item->{
            item.setCompany(companyService.detail(item.getCompanyId()));
            item.setResume(resumeService.detail(item.getResumeId()));
            item.setStudent(studentService.detail(item.getStudentId()));
            item.setPost(postService.detail(item.getPostId()));
        });

        return Result.ok(pageInfo);
    }

    @RequiresRoles(type = Role.STUDENT)
    @PostMapping("student_send")
    @LogAnnotation()
    public Map<String,Object> student_send(@RequestBody  Send send){
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        send.setStudentId(userData.getId());
        PageInfo<Send> pageInfo = sendService.query(send);

        pageInfo.getList().forEach(item->{
            item.setCompany(companyService.detail(item.getCompanyId()));
            item.setResume(resumeService.detail(item.getResumeId()));
            item.setPost(postService.detail(item.getPostId()));
        });

        return Result.ok(pageInfo);
    }

}
