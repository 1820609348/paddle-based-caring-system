package com.app.c4_ai.service.Impl;

import com.app.c4_ai.entity.Task;
import com.app.c4_ai.mapper.TaskMapper;
import com.app.c4_ai.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
}
