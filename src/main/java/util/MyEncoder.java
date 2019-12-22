package util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocol.MyProcotol;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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

}
