package client;

import codec.KeyExchangeReqMsgEncoder;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.DirectoryMsg;
import msg.KeyExchangeReqMsg;
import msg.NodeListRequestMsg;
import msg.NodeListResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Node;
import pojo.NodeInfo;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    Client client;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NodeListRequestMsg nodeListRequestMsg=new NodeListRequestMsg();


        ctx.writeAndFlush(nodeListRequestMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof NodeListResponseMsg){
            Config.nodeList=((NodeListResponseMsg) msg).getNodeList();
            KeyExchangeReqMsg preKeyExchangeReqMsg=null;
            byte[] publicKey=null;
            for(Node node:Config.nodeList){
                KeyExchangeReqMsg keyExchangeReqMsg=new KeyExchangeReqMsg();
                if(preKeyExchangeReqMsg!=null) {
                    keyExchangeReqMsg.setIp(node.getIp());
                    keyExchangeReqMsg.setPort(node.getPort());
                    Util.keyExchangeReqMsgGen(publicKey,JSON.toJSONBytes(preKeyExchangeReqMsg),keyExchangeReqMsg);
                }
                publicKey=node.getPublicKey();
                preKeyExchangeReqMsg=keyExchangeReqMsg;

            }
            KeyExchangeReqMsg keyExchangeReqMsg=new KeyExchangeReqMsg();
            Util.keyExchangeReqMsgGen(publicKey, JSON.toJSONBytes(preKeyExchangeReqMsg),keyExchangeReqMsg);
            Config.messageQueue.add(keyExchangeReqMsg);

            List<ChannelHandler> channelHandlerList=new ArrayList<>();
            channelHandlerList.add(new KeyExchangeReqMsgEncoder());
            channelHandlerList.add(new KeyExchangeHandler());

            Node lastNode=Config.nodeList.get(Config.nodeList.size()-1);
            Util.connect(lastNode.getIp(),lastNode.getPort(),channelHandlerList);
        }
    }
}
