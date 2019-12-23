package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class NettyServer {


    public static void main(String[] args) {
        // 配置IO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)	//设置NIO的模式
                    .option(ChannelOption.SO_BACKLOG, 1024)	//设置TCP缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32*1024)	// 设置发送数据的缓存大小
                    .option(ChannelOption.SO_RCVBUF, 32*1024)	// 设置接受数据的缓存大小
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)	// 设置保持连接
                    .childOption(ChannelOption.SO_SNDBUF, 32*1024)
                    .childHandler(new ServerHandler());
            // 绑定端口，同步等待绑定成功
            ChannelFuture f = b.bind(8888).sync();
            System.out.println("server start ok.....");
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}