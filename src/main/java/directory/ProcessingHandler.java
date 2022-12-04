package directory;

import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.DirectoryMsg;
import msg.NodeListRequestMsg;
import msg.NodeListResponseMsg;
import msg.ResponseMsg;
import util.Util;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


public class ProcessingHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof DirectoryMsg) {
            DirectoryMsg requestData = (DirectoryMsg) msg;
            ResponseMsg responseMsg = new ResponseMsg();
            String host = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
            Util.nodeList.add(host + ":" + requestData.getPort());
            responseMsg.setIntValue(requestData.getPort() * 2);
            ChannelFuture future = ctx.writeAndFlush(responseMsg);
            future.addListener(ChannelFutureListener.CLOSE);
            System.out.println(requestData);
        } else if(msg instanceof NodeListRequestMsg){

            NodeListResponseMsg nodeListResponseMsg=new NodeListResponseMsg();
            nodeListResponseMsg.setNodeList(Util.nodeList);
            System.out.println("NodeListResponseMsg:"+JSON.toJSONString(nodeListResponseMsg));
            ChannelFuture future =ctx.writeAndFlush(nodeListResponseMsg);
            future.addListener(ChannelFutureListener.CLOSE);


        }
    }
}