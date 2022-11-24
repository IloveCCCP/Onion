package msg;

public class NodeListRequestMsg extends Msg{


    MsgType msgType=MsgType.GET_NODES;

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

}
