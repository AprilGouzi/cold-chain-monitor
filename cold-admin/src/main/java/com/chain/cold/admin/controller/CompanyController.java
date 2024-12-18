package com.chain.cold.admin.controller;

import com.chain.cold.admin.service.CompanyService;
import com.chain.cold.common.admin.entity.CompanyEntity;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

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

    /**
     * 信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        CompanyEntity company = companyService.getById(id);

        return Result.ok().put("company", company);
    }


    /**
     * 保存
     *
     * @param company
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CompanyEntity company) {
        String uuid = UUID.randomUUID().toString();
        company.setId(uuid);

        companyService.save(company);

        return Result.ok();
    }

    /**
     * 修改
     *
     * @param company
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CompanyEntity company) {
        companyService.updateById(company);

        return Result.ok();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(String id) {
        companyService.removeById(id);

        return Result.ok();
    }
}
