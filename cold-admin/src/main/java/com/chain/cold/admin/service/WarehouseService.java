package com.chain.cold.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.admin.entity.WarehouseEntity;
import com.chain.cold.common.utils.PageUtils;

import java.util.Map;

/**
 * 仓库表
 *
 */
public interface WarehouseService extends IService<WarehouseEntity> {

    PageUtils queryPage(Map<String, Object> params);




}

