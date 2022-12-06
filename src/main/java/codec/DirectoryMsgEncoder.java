package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.DirectoryMsg;

public class DirectoryMsgEncoder extends MessageToByteEncoder<DirectoryMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, DirectoryMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getMsgType().getValue());
        out.writeInt(msg.getPort());
        out.writeInt(msg.getPublicKey().length);
        out.writeBytes(msg.getPublicKey());
    }
}