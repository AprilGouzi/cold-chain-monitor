package com.chain.cold.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.common.user.dao.SysUserDao;
import com.chain.cold.common.user.entity.SysUserEntity;
import com.chain.cold.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author AprilGouzi
 * version 1.0
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Override
    public SysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }
}
