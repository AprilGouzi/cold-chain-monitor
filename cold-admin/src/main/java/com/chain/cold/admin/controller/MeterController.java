package com.chain.cold.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.chain.cold.admin.service.MeterService;
import com.chain.cold.common.admin.entity.MeterEntity;
import com.chain.cold.common.utils.JedisUtil;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.common.utils.Result;
import com.chain.cold.common.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

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

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        MeterEntity meter = meterService.getById(id);

        return Result.ok().put("meter", meter);
    }

    /**
     * 保存仪表
     */
    @RequestMapping("/save")
    public Result save(@RequestBody MeterEntity meter) throws Exception {
        String uuid = UUID.randomUUID().toString();
        meter.setId(uuid);
        meterService.save(meter);

        try {
            //保存仪表信息到redis中
            JedisUtil.Strings strings = JedisUtil.getInstance().new Strings();
            strings.set(meter.getMetercode(), SerializeUtil.serialize(meter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Result.ok();
    }

    /**
     * 修改仪表
     */
    @RequestMapping("/update")
    public Result update(@RequestBody MeterEntity meter) {
        meterService.updateById(meter);

        JedisUtil.getInstance().getJedis().del(meter.getMetercode());

        JedisUtil.Strings strings = JedisUtil.getInstance().new Strings();
        //序列化，用于网络传输
        strings.set(meter.getMetercode(), SerializeUtil.serialize(meter));

        return Result.ok();
    }

    /**
     * 删除仪表
     */
    @RequestMapping("/delete")
    public Result delete(String id) {
        meterService.removeById(id);

        JedisUtil.getInstance().getJedis().del(meterService.getById(id).getMetercode());

        return Result.ok();
    }
}
