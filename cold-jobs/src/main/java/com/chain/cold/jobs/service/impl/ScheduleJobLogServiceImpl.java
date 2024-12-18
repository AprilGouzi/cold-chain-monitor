package com.chain.cold.jobs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chain.cold.common.job.dao.ScheduleJobLogDao;
import com.chain.cold.common.job.entity.ScheduleJobLogEntity;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.jobs.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //分页参数
        int current = params.get("page")== null? 1 : Integer.valueOf(params.get("page").toString());
        int size = params.get("pagesize") == null? 10 : Integer.valueOf(params.get("pagesize").toString());
        Page<ScheduleJobLogEntity> page = new Page<>(current, size);

        //查询参数
        String beanName = params.get("beanName")==null ? "": params.get("beanName").toString();
        QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .like(ScheduleJobLogEntity::getBeanName, beanName)
                .orderByDesc(ScheduleJobLogEntity::getCreateTime);


        IPage<ScheduleJobLogEntity> result = this.page(page, wrapper);

        return new PageUtils(result);
    }
}
