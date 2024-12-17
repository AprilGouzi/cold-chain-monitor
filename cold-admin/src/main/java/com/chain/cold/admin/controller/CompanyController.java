package com.chain.cold.admin.controller;

import com.chain.cold.admin.service.CompanyService;
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
 * 企业信息表
 */
@RestController
@RequestMapping("admin/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = companyService.queryPage(params);

        return Result.ok(page.getPageMap());
    }
}
