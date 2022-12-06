package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.KeyExchangeReqMsg;

public class KeyExchangeReqMsgEncoder extends MessageToByteEncoder<KeyExchangeReqMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, KeyExchangeReqMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getType().getValue());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getPayLoad());
        out.writeInt(msg.getAesKeyEncLength());
        out.writeBytes(msg.getAesKeyEnc());
    }
}
