package coder;

import io.netty.buffer.ByteBuf;
import msg.Msg;
import msg.NodeListRequestMsg;

public class NodeListRequestMsgDecoder extends Decoder{


    @Override
    public Msg decode(ByteBuf in) {
        NodeListRequestMsg nodeListRequestMsg=new NodeListRequestMsg();
        return nodeListRequestMsg;
    }
}
