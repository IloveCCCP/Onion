package node;

import codec.KeyExchangeReqMsgEncoder;
import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import msg.KeyExchangeReqMsg;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.DecryptObj;
import pojo.NodeInfo;
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
                if(json.isBlank()){
                    return;
                }
                KeyExchangeReqMsg keyExchangeReqMsg= JSON.parseObject(json, KeyExchangeReqMsg.class);
                Config.aesKey= decryptObj.getAesKey();
                if(keyExchangeReqMsg.getPayLoad()==null){
                    return;
                }
                KeyExchangeReqMsg keyExchangeReqMsg1=new KeyExchangeReqMsg();
                keyExchangeReqMsg1.setPayLoad(keyExchangeReqMsg.getPayLoad());
                Config.keyExchangeQueue.add(keyExchangeReqMsg1);
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
