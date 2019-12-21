package util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protocol.MyProcotol;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 自定义解码器
 */
public class MyDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        ByteBuf byteBuf = ctx.alloc().heapBuffer(10240);
        byte header = in.readByte();
        byte version = in.readByte();
        byte cmd = in.readByte();
        int code = in.readInt();
        byte encryp = in.readByte();
        long contentLength = in.readLong();
        String content = in.readBytes(in.readableBytes()).toString(Charset.defaultCharset());
        MyProcotol procotol = new MyProcotol(header, version, cmd, code, encryp, contentLength, content);
        channelReadComplete(ctx);
        out.add(procotol);
    }

}
