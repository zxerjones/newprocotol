package util;

import constant.Constant;
import constant.EncrypEnum;
import constant.MethodEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protocol.MyProcotol;

import java.awt.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 自定义解码器
 */
public class MyDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        ByteBuf byteBuf = ctx.alloc().heapBuffer(10240);
//        short header = in.readShort();
////        byte version = in.readByte();
////        int cmd = in.readInt();
////        int code = in.readInt();
////        byte encryp = in.readByte();
        long contentLength = in.readLong();
        String content = in.readBytes(in.writerIndex() - in.readerIndex()).toString(Charset.defaultCharset());
        MyProcotol procotol = new MyProcotol(Constant.HEADER, Constant.VERSION, MethodEnum.CLIENT_DATA_SAVE_IN_DB.getCode()
                , Constant.CODE, EncrypEnum.code, contentLength, content);
        out.add(procotol);
    }

}
