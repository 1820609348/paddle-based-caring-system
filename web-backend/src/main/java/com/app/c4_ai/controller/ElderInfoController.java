package com.app.c4_ai.controller;

import com.app.c4_ai.commons.Result;
import com.app.c4_ai.entity.ElderInfo;
import com.app.c4_ai.entity.ElderInfo;
import com.app.c4_ai.service.ElderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/elder")
public class ElderInfoController {
    @Autowired
    private ElderInfoService elderInfoService;
    //測試用接口
    @GetMapping("/get/{id}")
    public ElderInfo getUserById(@PathVariable("id")int uid){
        System.out.println("调用！");
        return elderInfoService.getById(uid);
    }

    @PostMapping("/save")
    public Result save(@RequestBody ElderInfo elderInfo){
        return elderInfoService.save(elderInfo)?Result.suc():Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return elderInfoService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/mod")
    public Result mod(@RequestBody ElderInfo elderInfo){
        return elderInfoService.updateById(elderInfo)?Result.suc():Result.fail();
    }

    @PostMapping("/listP")
    public Result listP(@RequestBody ElderInfo elderInfo){
        LambdaQueryWrapper<ElderInfo> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(elderInfo.getName())) {
            lambdaQueryWrapper.like(ElderInfo::getName,elderInfo.getName());
        }
        return Result.suc(elderInfoService.list(lambdaQueryWrapper));
    }
}
