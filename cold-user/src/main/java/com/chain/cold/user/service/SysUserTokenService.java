package com.chain.cold.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.user.entity.SysUserEntity;
import com.chain.cold.common.user.entity.SysUserTokenEntity;
import com.chain.cold.common.utils.Result;

/**
 * @author AprilGouzi
 * version 1.0
 * 用户token
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    Result createToken(Long userId);
}
