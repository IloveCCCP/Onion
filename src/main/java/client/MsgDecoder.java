package client;

import coder.Decoder;
import coder.NodeListRequestMsgDecoder;
import coder.NodeListResponseMsgDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;
import msg.Msg;
import msg.MsgType;
import msg.NodeListRequestMsg;

import java.util.List;

public class MsgDecoder extends ReplayingDecoder<NodeListRequestMsg> {

    Decoder decoder;

    Msg msg;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int type= in.readInt();
        if(type== MsgType.NODE_LIST_RESP.getValue()){
            decoder=new NodeListResponseMsgDecoder();

        }

        out.add( decoder.decode(in));
    }
}
