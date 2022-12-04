package client;

import codec.Decoder;
import codec.NodeListRespMsgDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import msg.Msg;
import msg.MsgType;

import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {

    Decoder decoder;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int type= in.readInt();
        if(type== MsgType.NODE_LIST_RESP.getValue()){
            decoder=new NodeListRespMsgDecoder();

        }

        out.add( decoder.decode(in));
    }
}
