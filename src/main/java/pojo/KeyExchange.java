package pojo;

import msg.KeyExchangeReqMsg;

import java.util.Base64;

public class KeyExchange {

    String aesKeyEnc;
    int aesKeyEncLength;
    String ip;
    int port;
    String payload;
    int length;
    String type;

    public KeyExchangeReqMsg convertToMsg(){
        KeyExchangeReqMsg keyExchangeReqMsg=new KeyExchangeReqMsg();
        keyExchangeReqMsg.setPort(port);
        keyExchangeReqMsg.setIp(ip);
        keyExchangeReqMsg.setPayload(Base64.getDecoder().decode(payload));
        keyExchangeReqMsg.setAesKeyEnc(Base64.getDecoder().decode(aesKeyEnc));
        return keyExchangeReqMsg;
    }

    public String getAesKeyEnc() {
        return aesKeyEnc;
    }

    public void setAesKeyEnc(String aesKeyEnc) {
        this.aesKeyEnc = aesKeyEnc;
    }

    public int getAesKeyEncLength() {
        return aesKeyEncLength;
    }

    public void setAesKeyEncLength(int aesKeyEncLength) {
        this.aesKeyEncLength = aesKeyEncLength;
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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
