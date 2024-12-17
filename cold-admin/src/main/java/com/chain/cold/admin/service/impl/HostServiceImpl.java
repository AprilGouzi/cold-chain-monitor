package com.chain.cold.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.admin.service.HostService;
import com.chain.cold.common.admin.dao.HostDao;
import com.chain.cold.common.admin.entity.HostEntity;
import com.chain.cold.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("hostService")
public class HostServiceImpl extends ServiceImpl<HostDao, HostEntity> implements HostService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int current = params.get("page") == null ? 1 : Integer.valueOf(params.get("page").toString());
        int size = params.get("pagesize") == null ? 10 : Integer.valueOf(params.get("pagesize").toString());

        Page<HostEntity> page = new Page<>(current, size);

        String housename = params.get("housename") == null ? "" : params.get("housename").toString();
        QueryWrapper<HostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(HostEntity::getHostname, housename).orderByAsc(HostEntity::getHostname);

        IPage<HostEntity> pageResult = this.page(page, queryWrapper);
        return new PageUtils(pageResult);
    }
}
