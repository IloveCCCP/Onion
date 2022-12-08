package pojo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Base64;

public class Node {

    String ip;
    int port;

    SecretKey aesKey;
    byte[] publicKey;

    String publicKeyString;
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
        this.publicKeyString= Base64.getEncoder().encodeToString(publicKey);
    }

    public SecretKey getAesKey() {
        return aesKey;
    }

    public void setAesKey(SecretKey aesKey) {
        this.aesKey = aesKey;
    }

    public String getPublicKeyString() {
        return publicKeyString;
    }
}
