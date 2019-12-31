import client.SimpleClientHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.local.LocalChannel;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;


/**
 * @ClassName TestChannelfUTURE
 * @Description TODO
 * @Author zhengxin
 * @Date 2019/12/31 16:47
 */
public class TestChannelFuture {
    @Test
    public void testof() {
        Channel channel = new LocalChannel();
        ChannelFuture channelFuture = channel.connect(new InetSocketAddress("127.0.0.1", 8888));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    ByteBuf byteBuf = Unpooled.copiedBuffer("hello", Charset.defaultCharset());
                    ChannelFuture wf = future.channel().writeAndFlush(byteBuf);
                } else {
                    Throwable throwable = future.cause();
                    throwable.printStackTrace();
                }
            }
        });
    }

}
