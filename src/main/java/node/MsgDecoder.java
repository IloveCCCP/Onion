package node;

import codec.Decoder;
import codec.KeyExchangeRespMsgDecoder;
import codec.RespMsgDecoder;
import codec.RespMsgEncoder;
import msg.MsgType;
import msg.RespMsg;
import msg.ResponseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MsgDecoder extends ReplayingDecoder<ResponseMsg> {

    Decoder decoder;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int type= in.readInt();
        if(MsgType.KEY_EXCHANGE_REQ.getValue()==type) {
            decoder = new KeyExchangeRespMsgDecoder();

        } else if(MsgType.RESP.getValue()==type){

            decoder=new RespMsgDecoder();
        }

        out.add(decoder.decode(in));
    }
}