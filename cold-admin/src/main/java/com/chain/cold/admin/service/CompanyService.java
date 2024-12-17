package com.chain.cold.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.admin.entity.CompanyEntity;
import com.chain.cold.common.utils.PageUtils;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 * 企业信息表
 */
public interface CompanyService extends IService<CompanyEntity> {
    PageUtils queryPage(Map<String, Object> params);

}
