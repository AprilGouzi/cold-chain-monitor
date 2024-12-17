package com.chain.cold.admin.controller;

import com.chain.cold.admin.service.HostService;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
}
