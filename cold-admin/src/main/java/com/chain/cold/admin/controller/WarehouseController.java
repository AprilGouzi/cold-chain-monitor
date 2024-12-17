package com.chain.cold.admin.controller;

import com.chain.cold.admin.service.WarehouseService;
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
 * 仓库表
 */
@RestController
@RequestMapping("admin/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = warehouseService.queryPage(params);

        return Result.ok(page.getPageMap());
    }
}
