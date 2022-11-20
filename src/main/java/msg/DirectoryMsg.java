package msg;

public class DirectoryMsg extends Msg{

    MsgType msgType=MsgType.REGISTER;

    private byte[] publicKey;
    private int port;
    private String ip;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    @Override
    public String toString() {
        return "DirectoryMsg{" + "port=" + port + ", ip='" + ip + '\'' + '}';
    }
}