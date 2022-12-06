package pojo;

import javax.crypto.SecretKey;

public class Node {

    String ip;
    int port;

    SecretKey aesKey;
    byte[] publicKey;

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

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public SecretKey getAesKey() {
        return aesKey;
    }

    public void setAesKey(SecretKey aesKey) {
        this.aesKey = aesKey;
    }
}
