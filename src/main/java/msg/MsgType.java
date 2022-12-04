package msg;

public enum MsgType {
    REGISTER(0), GET_NODES(1), NODE_LIST_RESP(2);

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
    MsgType(int value) {
        this.value=value;
    }


}
