package msg;

import pojo.Message;

import java.util.Base64;

public class MessageMsg extends Msg{

    MsgType type=MsgType.MESSAGE;

    String ip;

    int port;

    byte[] payload;

    int length;

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

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        this.length=payload.length;
    }

    public MsgType getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public Message convert(){
        Message message=new Message();
        message.setIp(ip);
        message.setPort(port);
        message.setPayload(Base64.getEncoder().encodeToString(payload));
        return message;
    }
}
