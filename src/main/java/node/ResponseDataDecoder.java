package node;

import msg.ResponseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseMsg> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseMsg data = new ResponseMsg();
        data.setIntValue(in.readInt());
        out.add(data);
    }
}