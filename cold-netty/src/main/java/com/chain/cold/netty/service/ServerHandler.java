package com.chain.cold.netty.service;

import com.alibaba.fastjson.JSON;
import com.chain.cold.netty.common.SpringContextUtils;
import com.chain.cold.netty.entity.MessageEntity;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author AprilGouzi
 * version 1.0
 * 硬件报文处理,数据报文:
 * SHT|HE00120931|29|76|1|null|null|20190802125717|SH5lb1.2.1R
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    public final static String MSG_TOPIC = "device_msg_topic";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("ServerHandler.channelRead()");
        ByteBuf in = (ByteBuf) msg;
        try {
            //接收报文
            if (in.readableBytes() <= 0) {
                return;
            }
            byte[] req = new byte[in.readableBytes()];
            in.readBytes(req);
            String body = new String(req, "UTF-8");
            logger.info("报文内容:{}", body);

            //解析报文
            MessageEntity message = this.parseMessage(body);
            String jsonString = JSON.toJSONString(message);
            logger.info("报文解析结果:{}", jsonString);

            //发送到kafka队列
            KafkaSender sender = (KafkaSender) SpringContextUtils.getBean("kafkaSender");
            sender.send(MSG_TOPIC, jsonString);
            logger.info("增加报文-仪表号:{}内容:{}", message.getMetercode(), body);

            //向硬件回送响应
            String responseText = "SHT|true|\0";
            ByteBuf buf = ctx.alloc().buffer();
            if (responseText.length() > 0) {
                buf.writeBytes(responseText.getBytes());
                ctx.writeAndFlush(buf);
                if (null != buf) {
                    buf.resetWriterIndex();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            //使用完ByteBuf之后，需要主动去释放资源，否则，资源一直在内存中加载，容易造成内存泄露
            in.resetWriterIndex();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 写一个空的buf，并刷新写出区域，完成后关闭sock channel连接
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭发送异常的连接
        ctx.close();
    }

    /**
     * 解析报文
     *
     * @param body
     * @return
     */
    private MessageEntity parseMessage(String body) {
        String[] arrBody = body.split("\\|");
        logger.info("报文字段数量:{}", arrBody.length);

        MessageEntity msg = new MessageEntity();
        msg.setMetercode(arrBody[1]);
        msg.setTem(Integer.valueOf(arrBody[2]));
        msg.setHum(Integer.valueOf(arrBody[3]));
        msg.setState(Integer.valueOf(arrBody[4]));
        msg.setLon(arrBody[5]);
        msg.setLat(arrBody[6]);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String sysdate = dateFormat.format(new Date());
        msg.setCurtime(sysdate);

        return msg;
    }
}
