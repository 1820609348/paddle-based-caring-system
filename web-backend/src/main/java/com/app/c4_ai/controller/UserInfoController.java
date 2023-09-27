package com.app.c4_ai.controller;

import com.app.c4_ai.commons.Result;
import com.app.c4_ai.entity.UserInfo;
import com.app.c4_ai.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    //測試用接口
    @GetMapping("/get/{id}")
    public UserInfo getUserById(@PathVariable("id")int uid){
        System.out.println("调用！");
        return userInfoService.getById(uid);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserInfo userInfo){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",userInfo.getUsername());
        List<UserInfo> list1=userInfoService.list(wrapper);
        if(userInfo.getSecret().equals(list1.get(0).getSecret())){
            return Result.suc(list1.get(0));
        }else{
            return Result.fail();
        }
    }

    @PostMapping("/save")
    public Result save(@RequestBody UserInfo user){
        List list=userInfoService.lambdaQuery()
                .eq(UserInfo::getUsername,user.getUsername()).list();
        if(list.size()!=0){
            System.out.println("重复账号注册！");
            return Result.fail("重复账号注册！");
        }
        return userInfoService.save(user)?Result.suc():Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return userInfoService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/mod")
    public Result mod(@RequestBody UserInfo user){
        return userInfoService.updateById(user)?Result.suc():Result.fail();
    }

    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody UserInfo user){
        return userInfoService.saveOrUpdate(user)?Result.suc():Result.fail();
    }


}
