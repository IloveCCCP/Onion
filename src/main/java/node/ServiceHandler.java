package node;

import codec.KeyExchangeReqMsgEncoder;
import codec.MessageMsgEncoder;
import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.KeyExchangeReqMsg;
import msg.MessageMsg;
import pojo.DecryptObj;
import pojo.KeyExchange;
import util.Util;
import java.util.ArrayList;
import java.util.List;

public class ServiceHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof KeyExchangeReqMsg){

            try {

                DecryptObj decryptObj=Util.decKeyExchangeReqMsg((KeyExchangeReqMsg) msg);

                String json= new String(decryptObj.getDecryptedPayLoad());


                Config.aesKey= decryptObj.getAesKey();
                System.out.println("aesKey received:"+JSON.toJSONString(Config.aesKey));
                System.out.println("payload received:"+json);
                if(json.equals("null")){
                    return;
                }
                KeyExchange keyExchange= JSON.parseObject(json, KeyExchange.class);
                KeyExchangeReqMsg keyExchangeReqMsg=keyExchange.convertToMsg();
                Config.messageQueue.add(keyExchangeReqMsg);
                List<ChannelHandler> list=new ArrayList<>();
                list.add(new MsgDecoder());
                list.add(new KeyExchangeReqMsgEncoder());
                list.add( new ToNodeHandler());
                Util.connect(keyExchangeReqMsg.getIp(),keyExchangeReqMsg.getPort(),list);

            } catch (Exception e){
                e.printStackTrace();
            }
        } else if(msg instanceof MessageMsg){
            try {
                byte[] payload=Util.aesDecrypt(Config.aesKey, ((MessageMsg) msg).getPayload());
                String json=new String(payload);
                if(json.startsWith("\"message:")){
                    System.out.println("message received:"+ json);
                    return;
                } else {
                    System.out.println("message payload:"+json);
                }
                MessageMsg messageMsg=JSON.parseObject(json,MessageMsg.class);
                List<ChannelHandler> channelHandlerList=new ArrayList<>();
                channelHandlerList.add(new MessageMsgEncoder());
                channelHandlerList.add( new ToNodeHandler());
                Config.messageQueue.add(messageMsg);

                Util.connect(messageMsg.getIp(),messageMsg.getPort(),channelHandlerList);

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                ctx.close();
            }
        }

    }
}
