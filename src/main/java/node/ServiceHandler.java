package node;

import codec.KeyExchangeReqMsgEncoder;
import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.KeyExchangeReqMsg;
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
        }

    }
}
