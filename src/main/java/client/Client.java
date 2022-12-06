package client;

import codec.KeyExchangeReqMsgEncoder;
import codec.MessageMsgEncoder;
import codec.NodeListReqMsgEncoder;
import com.alibaba.fastjson2.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import msg.MessageMsg;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import pojo.Node;
import util.Util;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Client extends Thread{

    @PostConstruct
    void init(){
        Client client=new Client();
        client.start();
        input();
    }

    void input(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while (true){

            System.out.println("Enter message");

            sendMessage( myObj.nextLine());  // Read user input


        }
    }

    void sendMessage(String message){
        try {


            MessageMsg preMessageMsg=null;
            for (Node node : Config.nodeList) {
                MessageMsg messageMsg = new MessageMsg();
                messageMsg.setIp(node.getIp());
                messageMsg.setPort(node.getPort());
                if(preMessageMsg==null)
                    messageMsg.setPayload( Util.aesEncrypt(node.getAesKey(), JSON.toJSONBytes("message:"+message)));
                else
                    messageMsg.setPayload( Util.aesEncrypt(node.getAesKey(), JSON.toJSONBytes(preMessageMsg)));
                preMessageMsg=messageMsg;
            }

            Config.messageQueue.add(preMessageMsg);
            List<ChannelHandler> channelHandlerList=new ArrayList<>();
            channelHandlerList.add(new MessageMsgEncoder());
            channelHandlerList.add( new KeyExchangeHandler());
            Node lastNode=Config.nodeList.get(Config.nodeList.size()-1);
            Util.connect(lastNode.getIp(),lastNode.getPort(),channelHandlerList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        String host = "localhost";
        int DirectoryPort = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NodeListReqMsgEncoder(),  new MsgDecoder(), new ClientHandler());
                }
            });

            ChannelFuture f = b.connect(host, DirectoryPort).sync();

            f.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);



    }
}
