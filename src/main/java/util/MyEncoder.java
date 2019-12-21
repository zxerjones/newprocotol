package util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocol.MyProcotol;

import java.nio.charset.Charset;

public class MyEncoder extends MessageToByteEncoder<MyProcotol> {
    protected void encode(ChannelHandlerContext ctx, MyProcotol msg, ByteBuf out) throws Exception {

        out.writeShort(msg.getHeader());
        out.writeByte(msg.getVersion());
        out.writeInt(msg.getCmd());
        out.writeInt(msg.getCode());
        out.writeShort(msg.getEncryp());
        out.writeLong(msg.getContentLength());
        out.writeBytes(msg.getContent().getBytes(Charset.defaultCharset()));
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, MyProcotol msg, boolean preferDirect) throws Exception {
        return Unpooled.buffer(1024*1024);
    }
}
