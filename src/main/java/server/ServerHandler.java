package server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import util.MyDecoder;
import util.MyEncoder;
import util.ProcotolDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ServerHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 添加自定义协议编码工具
        ch.pipeline().addLast(new MyEncoder());

        // 解决发送json不完整的问题
        ch.pipeline().addLast(new ProcotolDecoder(10240, 13, 8));
        ch.pipeline().addLast(new MyDecoder());
//        ch.pipeline().addLast(new MyDecoder());
        // 处理网络IO
        ch.pipeline().addLast(new ShakeHandServerHandler());
        ch.pipeline().addLast(new SimpleServerHandler());
        ch.pipeline().addLast(new CycleSendServerHandler());
        System.out.println(ch.pipeline());
    }
}
