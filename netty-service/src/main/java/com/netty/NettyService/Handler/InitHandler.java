package com.netty.NettyService.Handler;

import com.netty.NettyService.pojo.InitBean;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class InitHandler {

    public void initHandler(ChannelPipeline ch, InitBean initBean) {

        /*编解码方式，后续根据协议，自己定义编解码方式*/
        ch.addLast("encoder",MqttEncoder.INSTANCE);
        ch.addLast("decoder",new MqttDecoder());

        /*
        //websocket协议编解码方式
        ch.addLast("httpCode", new HttpServerCodec());
        ch.addLast("aggregator", new HttpObjectAggregator(65536));
        ch.addLast("webSocketHandler",
                new WebSocketServerProtocolHandler("/mqtt", "mqtt, mqttv3.1, mqttv3.1.1"));
        ch.addLast("wsDecoder", new WebSocketFrameToByteBufDecoder());
        ch.addLast("wsEncoder", new ByteBufToWebSocketFrameEncoder());
        ch.addLast("decoder", new MqttDecoder());
        ch.addLast("encoder", MqttEncoder.INSTANCE);
        */

        /*心跳设定*/
        ch.addLast(new IdleStateHandler(initBean.getHeart(),0,0));
        /*通信处理类，处理连接，断开，收到信息等处理*/
        ch.addLast(new DefaultHandler());

    }
}
