package com.chain.cold.jobs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.job.entity.ScheduleJobEntity;
import com.chain.cold.common.utils.PageUtils;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 * 定时任务
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 分页读取定时任务列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存定时任务
     * @param scheduleJob
     */
    void saveJob(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     * @param scheduleJob
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 删除定时任务
     * @param jobIds
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 立即执行任务
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 暂停执行任务
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复定时任务
     * @param jobIds
     */
    void resume(Long[] jobIds);

}
