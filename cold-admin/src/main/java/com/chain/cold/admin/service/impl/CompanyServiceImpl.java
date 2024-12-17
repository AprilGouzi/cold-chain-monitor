package com.chain.cold.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.admin.service.CompanyService;
import com.chain.cold.common.admin.dao.CompanyDao;
import com.chain.cold.common.admin.entity.CompanyEntity;
import com.chain.cold.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, CompanyEntity> implements CompanyService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //分页参数
        int current = params.get("page") == null ? 1 : Integer.valueOf(params.get("page").toString());
        int size = params.get("pageSize") == null ? 10 : Integer.valueOf(params.get("pagesize").toString());

        Page<CompanyEntity> page = new Page<>(current, size);

        //查询条件
        String company = params.get("company") == null ? "" : params.get("company").toString();
        QueryWrapper<CompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(CompanyEntity::getCompany, company);

        //执行查询
        IPage<CompanyEntity> result = this.page(page, wrapper);

        return new PageUtils(result);
    }
}
