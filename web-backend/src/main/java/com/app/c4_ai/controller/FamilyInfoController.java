package com.app.c4_ai.controller;

import com.app.c4_ai.commons.Result;
import com.app.c4_ai.entity.FamilyInfo;
import com.app.c4_ai.service.FamilyInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/family")
public class FamilyInfoController {
    @Autowired
    private FamilyInfoService familyInfoService;
    //測試用接口
    @GetMapping("/get/{id}")
    public FamilyInfo getUserById(@PathVariable("id")int uid){
        System.out.println("调用！");
        return familyInfoService.getById(uid);
    }

    @PostMapping("/save")
    public Result save(@RequestBody FamilyInfo familyInfo){
        return familyInfoService.save(familyInfo)?Result.suc():Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return familyInfoService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/mod")
    public Result mod(@RequestBody FamilyInfo familyInfo){
        return familyInfoService.updateById(familyInfo)?Result.suc():Result.fail();
    }

    @PostMapping("/listP")
    public Result listP(@RequestBody FamilyInfo familyInfo){
        LambdaQueryWrapper<FamilyInfo> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(familyInfo.getName())) {
            lambdaQueryWrapper.like(FamilyInfo::getName,familyInfo.getName());
        }
        return Result.suc(familyInfoService.list(lambdaQueryWrapper));
    }
}
