package node;

import codec.DirectoryMsgEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;
import util.Util;

@Component
public class OnionNode extends Thread{


    void report(){
        Config.servicePort= Util.getAvailablePort();
        String host = "localhost";
        int directoryPort = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DirectoryMsgEncoder(), new MsgDecoder(), new NodeHandler());
                }
            });

            ChannelFuture f = b.connect(host, directoryPort).sync();

            f.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new MsgDecoder(),
                                    new ServiceHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(Config.servicePort).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        OnionNode onionNode =new OnionNode();

        onionNode.start();
        onionNode.report();

    }

}
