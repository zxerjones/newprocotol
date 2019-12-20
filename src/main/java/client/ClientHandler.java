package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import util.MyDecoder;
import util.MyEncoder;

import java.nio.charset.StandardCharsets;

public class ClientHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyEncoder());
        ch.pipeline().addLast(new MyDecoder());
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 处理网络IO
        ch.pipeline().addLast(new SimpleClientHandler());
    }
}
