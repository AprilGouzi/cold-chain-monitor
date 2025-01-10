package com.chain.cold.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chain.cold.common.netty.entity.MessageEntity;

import java.util.Map;

/**
 * @author AprilGouzi
 * version 1.0
 */
public interface MessageService extends IService<MessageEntity> {
    IPage queryMessageRealtime(Map<String,Object> params);

}
