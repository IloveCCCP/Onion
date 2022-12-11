package codec;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import io.netty.buffer.ByteBuf;
import msg.Msg;
import msg.NodeListResponseMsg;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NodeListRespMsgDecoder extends Decoder{


    @Override
    public Msg decode(ByteBuf in) {
        NodeListResponseMsg nodeListResponseMsg=new NodeListResponseMsg();
        int length=in.readInt();
        if(in.readableBytes()<length){
            in.resetReaderIndex();
            return null;
        }
        String listJson=in.readCharSequence(length, StandardCharsets.UTF_8).toString();
        nodeListResponseMsg.setNodeList(JSON.parseObject(listJson, ArrayList.class));
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("publicKey");
        System.out.println("NodeListResponseMsg:" + JSON.toJSONString(nodeListResponseMsg.convert(),filter, JSONWriter.Feature.PrettyFormat));


        return nodeListResponseMsg;
    }
}
