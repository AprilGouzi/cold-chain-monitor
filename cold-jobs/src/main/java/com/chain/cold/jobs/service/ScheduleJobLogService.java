package com.chain.cold.jobs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.job.entity.ScheduleJobLogEntity;
import com.chain.cold.common.utils.PageUtils;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 * 定时任务日志
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
