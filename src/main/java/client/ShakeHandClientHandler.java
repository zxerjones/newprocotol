package client;

import constant.Constant;
import constant.MethodEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.MessageSizeEstimator;
import protocol.MyProcotol;

import java.nio.charset.Charset;

/**
 * @ClassName ShakeHandHandler
 * @Description 初次建立连接握手包
 * @Author zhengxin
 * @Date 2019/12/23 18:47
 */
public class ShakeHandClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyProcotol myProcotol = buildReq();
        ctx.writeAndFlush(myProcotol);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端握手接收结果.."+ ((MyProcotol)msg).toString());
        //  不做任何处理，直接透传
        ctx.fireChannelRead(msg);
    }


    private MyProcotol buildReq() {
        MyProcotol myProcotol = new MyProcotol();
        myProcotol.setHeader(Constant.HEADER);
        myProcotol.setVersion(Constant.VERSION);
        myProcotol.setCmd(MethodEnum.SHAKE_HAND.getCode());
        myProcotol.setCode(Constant.CODE);
        myProcotol.setContent("我是握手请求");
        myProcotol.setContentLength(myProcotol.getContent().getBytes(Charset.defaultCharset()).length);
        return myProcotol;
    }

}
