package node;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.DirectoryMsg;
import util.Util;

public class ToNodeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        while (!Config.messageQueue.isEmpty()) {
            ctx.writeAndFlush(Config.messageQueue.poll());
        }
    }

}
