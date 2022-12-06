package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.NodeListRequestMsg;

public class KeyExchangeHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


        while (!Config.messageQueue.isEmpty()) {
            ctx.writeAndFlush(Config.messageQueue.poll());
        }
    }

}
