package com.chain.cold.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.admin.service.WarehouseService;
import com.chain.cold.common.admin.dao.WarehouseDao;
import com.chain.cold.common.admin.entity.WarehouseEntity;
import com.chain.cold.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("warehouseService")
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, WarehouseEntity> implements WarehouseService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int current = params.get("page")== null? 1 : Integer.valueOf(params.get("page").toString());
        int size = params.get("pagesize") == null? 10 : Integer.valueOf(params.get("pagesize").toString());

        Page<WarehouseEntity> page = new Page<>(current, size);
        QueryWrapper<WarehouseEntity> wrapper = new QueryWrapper<>();
        String company = params.get("company")==null ? "": params.get("company").toString();
        wrapper.lambda().like(WarehouseEntity::getCompanyname, company);

        IPage<WarehouseEntity> result = this.page(page, wrapper);

        return new PageUtils(result);
    }

}