package com.chain.cold.jobs.service;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author AprilGouzi
 * version 1.0
 * 模拟硬件：发送硬件报文
 * SHT|10666730220|00|aaaaaa|2|8|35|75|50|875|100|10000000|55085|26|34101|460|0|20190408000000|IMEI|864121016526443|SH5lb1.2.1R|
 */
public class ClientDeviceHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ClientDeviceHandler.class);
    private String msg;

    public ClientDeviceHandler(final String msg) {
        this.msg = msg;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // logger.info("ClientDeviceHandler.channelActive()");
        // String msg = "SHT|10666730220|00|aaaaaa|2|8|35|75|50|875|100|10000000|55085|26|34101|460|0|20190510123000|IMEI|864121016526443|SH5lb1.2.1R|";
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //logger.info("ClientDeviceHandler.channelRead()");
        this.logger.info("平台响应:{}",msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //logger.info("ClientDeviceHandler.channelReadComplete()");
        // 写一个空buf，并刷新,完成后关闭sock channel连接。
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //logger.info("ClientDeviceHandler.exceptionCaught()");
        cause.printStackTrace();
        ctx.close(); // 关闭发生异常的连接
    }
}
