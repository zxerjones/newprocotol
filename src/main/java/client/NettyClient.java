package client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {


    public static void main(String[] args) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 配置启动辅助类
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientHandler())
                    .option(ChannelOption.SO_BACKLOG, 1024);
            // 异步连接服务器，同步等待连接成功
            ChannelFuture f = b.connect("127.0.0.1", 8888).sync();
            System.out.println("client start ok.......");
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}