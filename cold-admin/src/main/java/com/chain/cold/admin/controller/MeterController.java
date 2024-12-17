package com.chain.cold.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.chain.cold.admin.service.MeterService;
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
@RequestMapping("admin/meter")
public class MeterController {

    @Autowired
    private MeterService meterService;

    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = meterService.queryPage(params);

        return Result.ok(page.getPageMap());
    }
}
