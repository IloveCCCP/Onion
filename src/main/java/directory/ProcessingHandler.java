package directory;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.DirectoryMsg;
import msg.ResponseMsg;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    List<String> list=new ArrayList<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        DirectoryMsg requestData = (DirectoryMsg) msg;
        ResponseMsg responseMsg = new ResponseMsg();
        String host = ((InetSocketAddress)ctx.channel().remoteAddress()).getAddress().getHostAddress();
        list.add(host+":"+requestData.getPort());
        responseMsg.setIntValue(requestData.getPort() * 2);
        ChannelFuture future = ctx.writeAndFlush(responseMsg);
        future.addListener(ChannelFutureListener.CLOSE);
        System.out.println(requestData);
    }
}