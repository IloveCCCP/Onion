package node;

import com.alibaba.fastjson2.JSON;
import msg.DirectoryMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.Util;

public class NodeHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DirectoryMsg msg = new DirectoryMsg();

        msg.setPort(Config.servicePort);
        msg.setPublicKey(Util.generateKey());
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(JSON.toJSONString(msg));

    }
}