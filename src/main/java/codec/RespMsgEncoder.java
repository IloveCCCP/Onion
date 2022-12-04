package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.RespMsg;

import java.nio.charset.StandardCharsets;

public class RespMsgEncoder extends MessageToByteEncoder<RespMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RespMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getType().getValue());
        out.writeInt(msg.getErrorCode().getValue());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getMsg().getBytes(StandardCharsets.UTF_8));
    }
}
