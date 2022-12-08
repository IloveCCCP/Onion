package node;

import codec.KeyExchangeReqMsgEncoder;
import codec.MessageMsgEncoder;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
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
                System.out.println("key exchange payload encrypted received:"+ JSON.toJSONString(((KeyExchangeReqMsg) msg).convert(), JSONWriter.Feature.PrettyFormat));
                DecryptObj decryptObj=Util.decKeyExchangeReqMsg((KeyExchangeReqMsg) msg);

                String json= new String(decryptObj.getDecryptedPayLoad());


                Config.aesKey= decryptObj.getAesKey();
                System.out.println("aesKey decrypted:"+JSON.toJSONString(Config.aesKey));
                System.out.println("key exchange payload decrypted:"+ Util.formatJson(json));
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
                System.out.println("message payload encrypted received:"+ JSON.toJSONString(((MessageMsg) msg).convert(), JSONWriter.Feature.PrettyFormat));
                byte[] payload=Util.aesDecrypt(Config.aesKey, ((MessageMsg) msg).getPayload());
                String json=new String(payload);
                if(json.startsWith("\"message:")){
                    System.out.println("message decrypted:"+ json);
                    return;
                }

                MessageMsg messageMsg=JSON.parseObject(json,MessageMsg.class);
                System.out.println("message payload decrypted:"+JSON.toJSONString(messageMsg.convert(),JSONWriter.Feature.PrettyFormat));
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
