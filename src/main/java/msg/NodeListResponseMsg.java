package msg;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;

public class NodeListResponseMsg {


    String nodeListJson;



    public List getNodeList() {
        return JSON.parseObject(nodeListJson , ArrayList.class);
    }

    public void setNodeList(List nodeList) {
        this.nodeListJson=JSON.toJSONString(nodeList);
    }






}
