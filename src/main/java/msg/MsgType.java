package msg;

public enum MsgType {
    REGISTER(0), GET_NODES(1), NODE_LIST_RESP(2), KEY_EXCHANGE_REQ(3), RESP(4);

    public int getValue() {
        return value;
    }

    private int value;
    MsgType(int value) {
        this.value=value;
    }


}
