package client;

import codec.KeyExchangeReqMsgEncoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONWriter;
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

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

import static client.Config.nodeList;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NodeListRequestMsg nodeListRequestMsg=new NodeListRequestMsg();


        ctx.writeAndFlush(nodeListRequestMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof NodeListResponseMsg){
            nodeList=((NodeListResponseMsg) msg).getNodeList();

            KeyExchangeReqMsg preKeyExchangeReqMsg=null;
            for(Node node: nodeList){
                KeyExchangeReqMsg keyExchangeReqMsg=new KeyExchangeReqMsg();
                keyExchangeReqMsg.setIp(node.getIp());
                keyExchangeReqMsg.setPort(node.getPort());

                SecretKey secretKey=Util.aesKeyGen();
                Util.keyExchangeReqMsgGen(node.getPublicKey(), JSON.toJSONBytes(preKeyExchangeReqMsg),keyExchangeReqMsg,secretKey);
                node.setAesKey(secretKey);
                preKeyExchangeReqMsg=keyExchangeReqMsg;

            }

            Config.messageQueue.add(preKeyExchangeReqMsg);

            List<ChannelHandler> channelHandlerList=new ArrayList<>();
            channelHandlerList.add(new KeyExchangeReqMsgEncoder());
            channelHandlerList.add(new KeyExchangeHandler());

            Node lastNode= nodeList.get(nodeList.size()-1);
            Util.connect(lastNode.getIp(),lastNode.getPort(),channelHandlerList);
        }
    }
}
