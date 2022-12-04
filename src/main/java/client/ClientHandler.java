package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.DirectoryMsg;
import msg.NodeListRequestMsg;
import msg.NodeListResponseMsg;
import pojo.Node;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NodeListRequestMsg nodeListRequestMsg=new NodeListRequestMsg();


        ctx.writeAndFlush(nodeListRequestMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof NodeListResponseMsg){
            Config.nodeList=((NodeListResponseMsg) msg).getNodeList();
            for()
        }
    }
}
