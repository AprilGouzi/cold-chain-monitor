package com.chain.cold.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.admin.entity.MeterEntity;
import com.chain.cold.common.utils.PageUtils;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
public interface MeterService extends IService<MeterEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
