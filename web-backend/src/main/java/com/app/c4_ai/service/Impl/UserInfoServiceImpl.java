package com.app.c4_ai.service.Impl;

import com.app.c4_ai.entity.UserInfo;
import com.app.c4_ai.mapper.UserInfoMapper;
import com.app.c4_ai.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
