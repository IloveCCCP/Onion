package util;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import msg.KeyExchangeReqMsg;
import node.Config;
import pojo.DecryptObj;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.Socket;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Random;

public class Util {


    public static int getAvailablePort(){
        int max = 65535;
        int min = 2000;
        Random random = new Random();
        int port = random.nextInt(max)%(max-min+1) + min;
        boolean using = isLocalPortUsing(port);
        if (using){
            return getAvailablePort();
        }else {
            return port;
        }
    }

    public static boolean isLocalPortUsing(int port){
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    public static boolean isPortUsing(String host,int port) {
        boolean flag = false;
        try {
            InetAddress theAddress = InetAddress.getByName(host);
            Socket socket = new Socket(theAddress,port);
            flag = true;
        } catch (Exception e) {

        }
        return flag;
    }

    public static byte[] generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();
        Config.privateKey=pair.getPrivate();
        return pair.getPublic().getEncoded();
    }

    public static byte[] decrypt(byte[] encrypted) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, Config.privateKey);
        return decryptCipher.doFinal(encrypted);
    }

    public static byte[] encrypt(byte[] data, byte[] publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey)));
        return cipher.doFinal(data);
    }

    public static SecretKey aesKeyGen() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // for example
        SecretKey secretKey = keyGen.generateKey();
        return secretKey;
    }

    public static byte[] aesEncrypt(SecretKey key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, key);
        return aesCipher.doFinal(data);
    }

    public static byte[] aesDecrypt(byte[] key, byte[] enc) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey originalKey = new SecretKeySpec(key , 0, key .length, "AES");
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, originalKey);
        return aesCipher.doFinal(enc);
    }
    public static KeyExchangeReqMsg keyExchangeReqMsgGen(byte[] publicKey, byte[] payload, KeyExchangeReqMsg keyExchangeReqMsg, SecretKey secretKey) throws NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {

        keyExchangeReqMsg.setAesKeyEnc(Util.encrypt(secretKey.getEncoded(),publicKey));
        keyExchangeReqMsg.setPayload(Util.aesEncrypt(secretKey, payload));
        return keyExchangeReqMsg;


    }

    public static DecryptObj decKeyExchangeReqMsg(KeyExchangeReqMsg keyExchangeReqMsgEnc) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
         byte[] aesKey= decrypt(keyExchangeReqMsgEnc.getAesKeyEnc());
        DecryptObj decryptObj=new DecryptObj();
        decryptObj.setAesKey(aesKey);
        decryptObj.setDecryptedPayLoad(Util.aesDecrypt(decryptObj.getAesKey(),keyExchangeReqMsgEnc.getPayload()));
         return decryptObj;


    }

    public static void connect(String ip, int port, List<ChannelHandler> channelHandlerList){
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    for(ChannelHandler channelHandler:channelHandlerList)
                        ch.pipeline().addLast(channelHandler);
                }
            });

            ChannelFuture f = b.connect(ip, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
        }
    }




}