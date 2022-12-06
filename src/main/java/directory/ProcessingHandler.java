package directory;

import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.*;
import pojo.Node;
import util.Util;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


public class ProcessingHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof DirectoryMsg) {
            DirectoryMsg requestData = (DirectoryMsg) msg;
            String host = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
            Node node=new Node();
            node.setIp(host);
            node.setPort(requestData.getPort());
            node.setPublicKey(requestData.getPublicKey());
            Config.nodeList.add(node);
            RespMsg respMsg=new RespMsg();
            respMsg.setErrorCode(ErrorCode.Success);
            respMsg.setMsg("node added");
            ChannelFuture future = ctx.writeAndFlush(respMsg);
            //future.addListener(ChannelFutureListener.CLOSE);
            System.out.println(requestData);
        } else if(msg instanceof NodeListRequestMsg){

            NodeListResponseMsg nodeListResponseMsg=new NodeListResponseMsg();
            nodeListResponseMsg.setNodeList(Config.nodeList);
            System.out.println("NodeListResponseMsg:"+JSON.toJSONString(nodeListResponseMsg));
            ChannelFuture future =ctx.writeAndFlush(nodeListResponseMsg);
            //future.addListener(ChannelFutureListener.CLOSE);


        }
    }
}