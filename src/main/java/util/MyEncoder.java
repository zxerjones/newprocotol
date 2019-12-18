package util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocol.MyProcotol;

import java.nio.charset.Charset;

public class MyEncoder extends MessageToByteEncoder<MyProcotol> {
    protected void encode(ChannelHandlerContext ctx, MyProcotol msg, ByteBuf out) throws Exception {
        out.writeByte(msg.getHeader());
        out.writeByte(msg.getVersion());
        out.writeByte(msg.getCmd());
        out.writeInt(msg.getCode());
        out.writeByte(msg.getEncryp());
        out.writeLong(msg.getContentLength());
        out.writeBytes(msg.getContent().getBytes(Charset.defaultCharset()));
    }
}
