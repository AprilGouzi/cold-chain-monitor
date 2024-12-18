package com.chain.cold.admin.controller;

import com.chain.cold.admin.service.HostService;
import com.chain.cold.common.admin.entity.HostEntity;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * @author AprilGouzi
 * version 1.0
 */
@RestController
@RequestMapping("admin/host")
public class HostController {

    @Autowired
    private HostService hostService;

    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = hostService.queryPage(params);

        return Result.ok(page.getPageMap());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        HostEntity host = hostService.getById(id);

        return Result.ok().put("host", host);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody HostEntity host) {
        String uuid = UUID.randomUUID().toString();
        host.setId(uuid);
        hostService.save(host);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody HostEntity host) {
        hostService.updateById(host);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(String id) {
        hostService.removeById(id);

        return Result.ok();
    }
}
