package pojo;

import javax.crypto.SecretKey;

public class NodeInfo {
    String ip;
    int port;

    SecretKey aesKey;

    byte[] payload;

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
    }


    public SecretKey getAesKey() {
        return aesKey;
    }

    public void setAesKey(SecretKey aesKey) {
        this.aesKey = aesKey;
    }
}
