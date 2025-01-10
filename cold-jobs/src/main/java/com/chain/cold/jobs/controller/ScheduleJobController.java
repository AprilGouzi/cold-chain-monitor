package com.chain.cold.jobs.controller;

import com.chain.cold.common.job.entity.ScheduleJobEntity;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.common.utils.Result;
import com.chain.cold.common.utils.ValidatorUtils;
import com.chain.cold.jobs.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 * 定时任务
 */
@RestController
@RequestMapping("/system/schedule")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = scheduleJobService.queryPage(params);

        return Result.ok().put("page", page);
    }

    /**
     * 定时任务信息
     */
    @RequestMapping("/info/{jobId}")
    public Result info(@PathVariable Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
        return Result.ok().put("schedule", schedule);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.saveJob(scheduleJob);
        return Result.ok();
    }

    /**
     * 修改定时任务
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.update(scheduleJob);

        return Result.ok();
    }

    /**
     * 删除定时任务
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return Result.ok();
    }

    /**
     * 立即执行任务
     */
    @RequestMapping("/run")
    public Result run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);

        return Result.ok();
    }

    /**
     * 暂停任务
     */
    @RequestMapping("/pause")
    public Result pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);

        return Result.ok();
    }

    /**
     * 回复定时任务
     */
    @RequestMapping("/resume")
    public Result resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);

        return Result.ok();
    }
}
