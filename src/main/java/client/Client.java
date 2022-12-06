package client;

import codec.KeyExchangeReqMsgEncoder;
import codec.NodeListReqMsgEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class Client extends Thread{

    @PostConstruct
    void init(){
        Client client=new Client();
        client.start();

    }

    void input(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while (true){

            System.out.println("Enter message");

            String userName = myObj.nextLine();  // Read user input

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
