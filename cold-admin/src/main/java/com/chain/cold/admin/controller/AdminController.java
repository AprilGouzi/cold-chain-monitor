package com.chain.cold.admin.controller;

import com.chain.cold.common.admin.entity.OperationLog;
import com.chain.cold.common.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@RestController
@RequestMapping("admin/api")
public class AdminController {

    @RequestMapping("/total")
    public Result getTotal(@RequestParam Map<String,Object> params){
        Map<String,Object> map = new HashMap<>();
        map.put("equipmentTotal", "16");
        map.put("organizationTotal", "5");
        map.put("warningTotal","32342");
        map.put("meterTotal","16");
        map.put("accuracy", "57");
        map.put("unaccomplished", "32123");
        map.put("finishNumber", "2312");
        return Result.ok(map);
    }

    @RequestMapping("/operationlog")
    public Result getOperationLog(@RequestParam Map<String,Object> params){
        List<OperationLog> list = new ArrayList<OperationLog>();

        for(int i=0; i < 5;i ++){
            OperationLog log = new OperationLog();
            log.setName("admin");
            log.setMsg("开风机");
            log.setStorehouse("昌平一号库房");
            log.setOperationTime("2024-08-20 10:21:00");
            list.add(log);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", "1");
        map.put("total", "9");
        map.put("items",list);
        return Result.ok(map);

    }
}
