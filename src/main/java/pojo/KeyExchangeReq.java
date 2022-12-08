package pojo;

public class KeyExchangeReq {

    String aesKeyEnc;
    String payload;

    public String getAesKeyEnc() {
        return aesKeyEnc;
    }

    public void setAesKeyEnc(String aesKeyEnc) {
        this.aesKeyEnc = aesKeyEnc;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
