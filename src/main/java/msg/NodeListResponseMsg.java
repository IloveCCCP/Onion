package msg;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;

public class NodeListResponseMsg extends Msg{

    MsgType msgType=MsgType.NODE_LIST_RESP;

    public MsgType getMsgType() {
        return msgType;
    }
    String nodeListJson;

    int length;

    public int getLength() {
        return length;
    }



    public List getNodeList() {
        return JSON.parseObject(nodeListJson , ArrayList.class);
    }

    public String getNodeListJson(){
        return nodeListJson;
    }

    public void setNodeList(List nodeList) {
        this.nodeListJson=JSON.toJSONString(nodeList);
        length=nodeListJson.length();
    }






}
