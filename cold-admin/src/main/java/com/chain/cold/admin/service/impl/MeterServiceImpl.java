package com.chain.cold.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.admin.service.MeterService;
import com.chain.cold.common.admin.dao.MeterDao;
import com.chain.cold.common.admin.entity.MeterEntity;
import com.chain.cold.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("meterService")
public class MeterServiceImpl extends ServiceImpl<MeterDao, MeterEntity> implements MeterService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //分页参数
        int current = params.get("page") == null ? 1 : Integer.valueOf(params.get("page").toString());
        int size = params.get("pageSize") == null ? 10 : Integer.valueOf(params.get("pagesize").toString());

        Page<MeterEntity> page = new Page<>(current, size);
        String housename = params.get("housename") == null ? "" : params.get("housename").toString();

        QueryWrapper<MeterEntity> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().like(MeterEntity::getHousename, housename)
                .orderByAsc(MeterEntity::getMetername);

        IPage<MeterEntity> result = this.page(page, queryWrapper);
        return new PageUtils(result);
    }
}
