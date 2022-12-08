package directory;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
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
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
            filter.getExcludes().add("publicKey");
            System.out.println("node register:"+ JSON.toJSONString(node,filter));
        } else if(msg instanceof NodeListRequestMsg){

            NodeListResponseMsg nodeListResponseMsg=new NodeListResponseMsg();
            nodeListResponseMsg.setNodeList(Config.nodeList);
            ChannelFuture future =ctx.writeAndFlush(nodeListResponseMsg);


        }
    }
}