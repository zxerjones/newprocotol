package server;

import constant.Constant;
import constant.MethodEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MyProcotol;

/**
 * @ClassName ShakeHandServerHandler
 * @Description 初次握手响应，
 * @Author zhengxin
 * @Date 2019/12/23 18:56
 */
public class ShakeHandServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProcotol myProcotol = (MyProcotol) msg;
        if (myProcotol.getCmd() == MethodEnum.SHAKE_HAND.getCode()) {
            System.out.println("服务端收到握手请求............" + myProcotol.toString());
            MyProcotol response = buildResp();
            ctx.writeAndFlush(response);
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    private MyProcotol buildResp() {
        MyProcotol myProcotol = new MyProcotol();
        myProcotol.setHeader(Constant.HEADER);
        myProcotol.setVersion(Constant.VERSION);
        myProcotol.setCmd(MethodEnum.SHAKE_HAND.getCode());
        myProcotol.setCode(Constant.CODE);
        myProcotol.setContent("我是握手请求响应");
        myProcotol.setContentLength(myProcotol.getContent().getBytes().length);
        return myProcotol;
    }
}
