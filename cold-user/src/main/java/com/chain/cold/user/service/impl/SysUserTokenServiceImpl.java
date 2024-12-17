package com.chain.cold.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.common.user.dao.SysUserTokenDao;
import com.chain.cold.common.user.entity.SysUserTokenEntity;
import com.chain.cold.user.service.SysUserTokenService;
import org.springframework.stereotype.Service;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
}
