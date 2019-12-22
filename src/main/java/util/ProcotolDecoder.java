package util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import protocol.MyProcotol;

import java.nio.charset.Charset;

public class ProcotolDecoder extends LengthFieldBasedFrameDecoder {


    public ProcotolDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
//        System.out.println(super.decode(ctx,in));
//        if (in == null) {
//            return new MyProcotol();
//        }
//
//        short header = in.readShort();
//        byte version = in.readByte();
//        int cmd = in.readInt();
//        int code = in.readInt();
//        short encrpy = in.readShort();
//        long length = in.readLong();
//
//        byte[] bytes = new byte[in.readableBytes()];
//        in.readBytes(bytes);
//
//
//        byte[]  contentBytes = new byte[in.readableBytes()];
//        in.readBytes(contentBytes);
//
//        return new MyProcotol(header, version, cmd, code, encrpy, length, new String(contentBytes, Charset.defaultCharset()));
    }
}
