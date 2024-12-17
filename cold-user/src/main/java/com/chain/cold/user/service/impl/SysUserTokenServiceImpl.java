package com.chain.cold.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.common.user.dao.SysUserTokenDao;
import com.chain.cold.common.user.entity.SysUserTokenEntity;
import com.chain.cold.common.utils.Result;
import com.chain.cold.common.utils.TokenGenerator;
import com.chain.cold.user.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public Result createToken(Long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        Result r = Result.ok().put("token", token).put("expire", EXPIRE);
        return r;
    }

    @Override
    public void logout(String token) {
        this.getBaseMapper().deleteByToken(token);
    }
}
