package directory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.ResponseMsg;

public class ResponseDataEncoder extends MessageToByteEncoder<ResponseMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntValue());
    }
}