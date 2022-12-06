package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.MessageMsg;

public class MessageMsgEncoder extends MessageToByteEncoder<MessageMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getType().getValue());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getPayload());
    }
}
