package com.chain.cold.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.admin.service.MonitorService;
import com.chain.cold.common.admin.dao.MonitorDao;
import com.chain.cold.common.admin.entity.MonitorEntity;
import com.chain.cold.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("monitorService")
public class MonitorServiceImpl extends ServiceImpl<MonitorDao, MonitorEntity> implements MonitorService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int current = params.get("page")== null? 1 : Integer.valueOf(params.get("page").toString());
        int size = params.get("pagesize") == null? 10 : Integer.valueOf(params.get("pagesize").toString());

        Page<MonitorEntity> page = new Page<>(current, size);

        String username = params.get("username")==null ? "": params.get("username").toString();
        QueryWrapper<MonitorEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(MonitorEntity::getUsername, username);

        IPage<MonitorEntity> result = this.page(page, wrapper);

        return new PageUtils(result);
    }
}
