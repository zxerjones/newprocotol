package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import protocol.MyProcotol;
import util.Constant;
import util.EncrypEnum;
import util.MethodEnum;


/**
 * 客户端发送请求，从服务器拉取数据 cmd=0x11
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String content = "I an client";
        MyProcotol procotol = new MyProcotol();
        procotol.setHeader(Constant.HEADER);
        procotol.setVersion(Constant.VERSION);
        procotol.setCmd(MethodEnum.CLIENT_DATA_SAVE_IN_DB.getCode());
        procotol.setCode(Constant.CODE);
        procotol.setEncryp((byte) EncrypEnum.code);
        procotol.setContent(content);
        procotol.setContentLength(content.length());
        ctx.writeAndFlush(procotol);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProcotol procotol = (MyProcotol) msg;
        System.out.println("客户端收到服务端的信息:" + procotol.toString());
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}