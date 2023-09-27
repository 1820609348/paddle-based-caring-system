package com.app.c4_ai.controller;

import com.app.c4_ai.commons.Result;
import com.app.c4_ai.entity.Task;
import com.app.c4_ai.service.TaskService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    //測試用接口
    @GetMapping("/get/{id}")
    public Task getUserById(@PathVariable("id")int uid){
        System.out.println("调用！");
        return taskService.getById(1);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Task task){
        return taskService.save(task)?Result.suc():Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return taskService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/mod")
    public Result mod(@RequestBody Task task){
        return taskService.updateById(task)?Result.suc():Result.fail();
    }

    @PostMapping("/listP")
    public Result listP(@RequestBody Task task){
        LambdaQueryWrapper<Task> lambdaQueryWrapper=new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(task.getName())) {
            lambdaQueryWrapper.like(Task::getName,task.getName());
        }
        return Result.suc(taskService.list(lambdaQueryWrapper));
    }
}
