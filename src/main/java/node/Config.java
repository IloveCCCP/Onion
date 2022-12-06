package node;

import msg.KeyExchangeReqMsg;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.util.LinkedList;
import java.util.Queue;

public class Config {
    public static PrivateKey privateKey;

    public static byte[] aesKey;

    public static int servicePort;

    public static Queue<KeyExchangeReqMsg> keyExchangeQueue=new LinkedList();

    public static Queue messageQueue=new LinkedList();
}
