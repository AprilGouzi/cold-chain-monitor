package com.chain.cold.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chain.cold.common.netty.entity.MessageEntity;
import com.chain.cold.common.utils.Result;
import com.chain.cold.monitor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@RestController
@RequestMapping("device/monitor")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 仓库监控列表
     */
    @RequestMapping("/list")
    public Result deviceList(@RequestParam Map<String, Object> params) {
        List<MessageEntity> list = messageService.queryMessageRealtime(params).getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", list);
        return Result.ok(map);
    }

    /**
     * 实时监控数据
     */
    @RequestMapping("/realtime")
    public Result realtime(@RequestParam Map<String, Object> params) {
        IPage<MessageEntity> list = messageService.queryMessageRealtime(params);
        Map<String, Object> map = new HashMap<>();
        map.put("total", list.getTotal());
        map.put("page", list.getPages());
        map.put("items", list.getRecords());
        return Result.ok(map);
    }
}
