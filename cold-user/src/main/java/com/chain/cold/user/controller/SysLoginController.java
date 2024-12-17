package com.chain.cold.user.controller;

import com.chain.cold.common.user.entity.SysLoginForm;
import com.chain.cold.common.user.entity.SysUserEntity;
import com.chain.cold.common.utils.Result;
import com.chain.cold.user.service.SysUserService;
import com.chain.cold.user.service.SysUserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
@RestController
@RequestMapping("sys/user")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 登录
     *
     * @param form
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody SysLoginForm form) {
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在，密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return Result.error("账号或密码不正确");
        }

        //生成token,并保存到数据库
        Result r = sysUserTokenService.createToken(user.getUserId());
        return r;
    }

    /**
     * 退出
     *
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserTokenService.logout(token);
        return Result.ok();
    }

    /**
     * 用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result userInfo() {
        SysUserEntity user = sysUserService.queryByUserName("admin");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", user.getUsername());
        map.put("company", "韶关学院");
        map.put("phone", "");
        map.put("avatar", "");
        map.put("username", user.getUsername());
        map.put("logintime", "");
        return Result.ok(map);
    }
}
