package coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.NodeListRequestMsg;

public class NodeListRequestMsgEncoder extends MessageToByteEncoder<NodeListRequestMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NodeListRequestMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getMsgType().getValue());
    }
}
