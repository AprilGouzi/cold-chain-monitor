package com.chain.cold.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.user.entity.SysUserEntity;

/**
 * @author AprilGouzi
 * version 1.0
 * 系统用户
 */
public interface SysUserService extends IService<SysUserEntity> {

    SysUserEntity queryByUserName(String username);
}
