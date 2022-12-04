package codec;

import io.netty.buffer.ByteBuf;
import msg.Msg;
import msg.NodeListRequestMsg;

public class NodeListReqMsgDecoder extends Decoder{


    @Override
    public Msg decode(ByteBuf in) {
        NodeListRequestMsg nodeListRequestMsg=new NodeListRequestMsg();
        return nodeListRequestMsg;
    }
}
