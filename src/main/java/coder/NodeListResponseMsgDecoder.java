package coder;

import com.alibaba.fastjson2.JSON;
import io.netty.buffer.ByteBuf;
import msg.Msg;
import msg.NodeListResponseMsg;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NodeListResponseMsgDecoder extends Decoder{


    @Override
    public Msg decode(ByteBuf in) {
        NodeListResponseMsg nodeListResponseMsg=new NodeListResponseMsg();
        String listJson=in.readCharSequence(in.readInt(), StandardCharsets.UTF_8).toString();
        nodeListResponseMsg.setNodeList(JSON.parseObject(listJson, ArrayList.class));
        System.out.println("NodeListResponseMsg:" + JSON.toJSONString(nodeListResponseMsg));
        return nodeListResponseMsg;
    }
}
