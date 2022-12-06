package codec;

import io.netty.buffer.ByteBuf;
import msg.MessageMsg;
import msg.Msg;

public class MessageMsgDecoder extends Decoder{
    @Override
    public Msg decode(ByteBuf in) {
        MessageMsg messageMsg=new MessageMsg();
        byte[] payload=new byte[in.readInt()];
        in.readBytes(payload);
        messageMsg.setPayload(payload);
        return messageMsg;
    }
}
