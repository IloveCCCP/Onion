package pojo;

public class DecryptObj {

    byte[] aesKey;

    byte[] decryptedPayLoad;

    public byte[] getAesKey() {
        return aesKey;
    }

    public void setAesKey(byte[] aesKey) {
        this.aesKey = aesKey;
    }

    public byte[] getDecryptedPayLoad() {
        return decryptedPayLoad;
    }

    public void setDecryptedPayLoad(byte[] decryptedPayLoad) {
        this.decryptedPayLoad = decryptedPayLoad;
    }




}
