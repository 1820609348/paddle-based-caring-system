package com.app.c4_ai.service.Impl;

import com.app.c4_ai.entity.ElderInfo;
import com.app.c4_ai.mapper.ElderInfoMapper;
import com.app.c4_ai.service.ElderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ElderInfoServiceImpl extends ServiceImpl<ElderInfoMapper, ElderInfo> implements ElderInfoService {
}
