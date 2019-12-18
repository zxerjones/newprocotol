package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import util.MyDecoder;
import util.MyEncoder;

public class ClientHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyEncoder());
        ch.pipeline().addLast(new MyDecoder());
        // 处理网络IO
        ch.pipeline().addLast(new SimpleClientHandler());
    }
}
