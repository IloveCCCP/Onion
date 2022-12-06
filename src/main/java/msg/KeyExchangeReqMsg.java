package msg;

import javax.crypto.SecretKey;

public class KeyExchangeReqMsg extends Msg {
    MsgType type=MsgType.KEY_EXCHANGE_REQ;

    byte[] aesKey;

    String ip;

    int port;

    byte[] aesKeyEnc;

    int aesKeyEncLength;

    byte[] payLoad;

    int length;
    public MsgType getType() {
        return type;
    }

    public byte[] getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(byte[] payLoad) {
        this.payLoad = payLoad;
        this.length= payLoad.length;
    }

    public int getLength() {
        return length;
    }

    public byte[] getAesKeyEnc() {
        return aesKeyEnc;
    }

    public void setAesKeyEnc(byte[] aesKeyEnc) {
        this.aesKeyEnc = aesKeyEnc;
        this.aesKeyEncLength=aesKeyEnc.length;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte[] getAesKey() {
        return aesKey;
    }

    public void setAesKey(byte[] aesKey) {
        this.aesKey = aesKey;
    }

    public int getAesKeyEncLength() {
        return aesKeyEncLength;
    }
}
