package codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import msg.NodeListResponseMsg;

import java.nio.charset.StandardCharsets;

public class NodeListRespMsgEncoder extends MessageToByteEncoder<NodeListResponseMsg> {


    @Override
    protected void encode(ChannelHandlerContext ctx, NodeListResponseMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getMsgType().getValue());
        out.writeInt(msg.getLength());
        out.writeCharSequence(msg.getNodeListJson(), StandardCharsets.UTF_8);
    }
}
