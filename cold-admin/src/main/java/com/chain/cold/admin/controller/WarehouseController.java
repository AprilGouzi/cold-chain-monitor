package com.chain.cold.admin.controller;

import com.chain.cold.admin.service.WarehouseService;
import com.chain.cold.common.admin.entity.WarehouseEntity;
import com.chain.cold.common.utils.PageUtils;
import com.chain.cold.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = warehouseService.queryPage(params);

        return Result.ok(page.getPageMap());
    }

    /**
     * 实时仓库列表
     */
    @RequestMapping("/realTime")
    public Result realTime(@RequestParam Map<String, Object> params) {
        String companyId = params.get("companyId").toString();
        if (companyId.equals("0")) {
            params.remove("companyId");
        }

        PageUtils page = warehouseService.queryPage(params);
        List<WarehouseEntity> map = (List<WarehouseEntity>) page.getPageMap().get("items");
        for (int i = 0; i < map.size(); i++) {
            WarehouseEntity we = map.get(i);
            Map<String, String> tmpmap = new HashMap<>();
            tmpmap.put("lng", String.valueOf(we.getLongitude()));
            tmpmap.put("lat", String.valueOf(we.getLatitude()));
            we.setMarkerPoint(tmpmap);
        }
        page.getPageMap().remove("items");
        page.getPageMap().put("items", map);

        return Result.ok(page.getPageMap());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        WarehouseEntity warehouse = warehouseService.getById(id);

        return Result.ok().put("warehouse", warehouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody WarehouseEntity warehouse) {
        String uuid = UUID.randomUUID().toString();
        warehouse.setId(uuid);
        warehouseService.save(warehouse);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody WarehouseEntity warehouse) {
        warehouseService.updateById(warehouse);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(String id) {
        warehouseService.removeById(id);

        return Result.ok();
    }
}
