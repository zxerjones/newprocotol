package server;

import constant.Constant;
import constant.MethodEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MyProcotol;

/**
 * @ClassName CycleSendServerHandler
 * @Description TODO
 * @Author zhengxin
 * @Date 2019/12/23 18:58
 */
public class CycleSendServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProcotol myProcotol = (MyProcotol) msg;
        if (myProcotol.getCmd() == MethodEnum.CLIENT_CYCLE_SENG_MESSAGE.getCode()) {
            System.out.println("receive message  from client: " + myProcotol.toString());
            MyProcotol response = buildResp();
            System.out.println("send response message to client :" + response.toString());
            ctx.writeAndFlush(msg);
        } else {
            // 透传
            ctx.fireChannelRead(msg);
        }

    }


    /**
     * 构建对客户端响应
     * @return
     */
    private MyProcotol buildResp() {
        MyProcotol myProcotol = new MyProcotol();
        myProcotol.setHeader(Constant.HEADER);
        myProcotol.setVersion(Constant.VERSION);
        myProcotol.setCmd(MethodEnum.CLIENT_CYCLE_SENG_MESSAGE.getCode());
        myProcotol.setCode(Constant.CODE);
        return myProcotol;
    }
}
