package com.luolin.front;

import com.luolin.entity.Company;
import com.luolin.entity.Student;
import com.luolin.service.CompanyService;
import com.luolin.service.StudentService;
import com.luolin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/f/reg/")
public class RegFront {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("student_create")
    public Result student_create(@RequestBody Student student){

        Student param = new Student();
        param.setAccount(student.getAccount());
        int count = studentService.count(param);
        if(count>0){
            return Result.fail("注册失败，账号因存在！");
        }else {
            int flag = studentService.create(student);
            if(flag>0){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }
    }

    @PostMapping("company_create")
    public Result student_create(@RequestBody Company company){
        Company param = new Company();
        param.setAccount(company.getAccount());
        int count = companyService.count(param);
        if(count>0){
            return Result.fail("注册失败，账号因存在！");
        }else {
            int flag = companyService.create(company);
            if(flag>0){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }
    }


}
