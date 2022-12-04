package msg;

import msg.Msg;

public class KeyExchangeReqMsg extends Msg {
    MsgType type=MsgType.KEY_EXCHANGE_REQ;

    byte[] keyEnc;

    int length;

    public MsgType getType() {
        return type;
    }

    public byte[] getKeyEnc() {
        return keyEnc;
    }

    public void setKeyEnc(byte[] keyEnc) {
        this.keyEnc = keyEnc;
        this.length=keyEnc.length;
    }

    public int getLength() {
        return length;
    }
}
