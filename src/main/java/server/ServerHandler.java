package server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import util.MyDecoder;
import util.MyEncoder;

public class ServerHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 添加自定义协议编码工具
        ch.pipeline().addLast(new MyEncoder());
        ch.pipeline().addLast(new MyDecoder());
        // 处理网络IO
        ch.pipeline().addLast(new SimpleServerHandler());
    }
}
