package msg;

import pojo.KeyExchangeReq;

import java.util.Base64;

public class KeyExchangeReqMsg extends Msg {
    MsgType type=MsgType.KEY_EXCHANGE_REQ;
    String ip;

    int port;

    byte[] aesKeyEnc;

    int aesKeyEncLength;

    byte[] payload;

    int length;
    public MsgType getType() {
        return type;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        this.length= payload.length;
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

    public int getAesKeyEncLength() {
        return aesKeyEncLength;
    }

    public KeyExchangeReq convert(){
        KeyExchangeReq keyExchangeReq=new KeyExchangeReq();
        keyExchangeReq.setAesKeyEnc(Base64.getEncoder().encodeToString(aesKeyEnc));
        keyExchangeReq.setPayload(Base64.getEncoder().encodeToString(payload));
        return keyExchangeReq;
    }
}
