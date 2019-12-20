package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MyProcotol;
import service.ProcotolService;
import util.Constant;
import util.EncrypEnum;
import util.MethodEnum;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    private ProcotolService procotolService = new ProcotolService();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProcotol myProcotol = (MyProcotol) msg;
        System.out.println("服务端收到客户端的消息 : " + myProcotol.toString());
        String resp = "已经收到客户端的消息，";
        MyProcotol respPro = new MyProcotol();
        respPro.setHeader(Constant.HEADER);
        respPro.setVersion(Constant.VERSION);
        respPro.setCmd(myProcotol.getCmd());
        respPro.setCode(Constant.CODE);
        respPro.setEncryp((byte) EncrypEnum.code);
        respPro.setContentLength(resp.length());

        // 做具体的业务处理，保存数据或者向客户端推送数据
        String response = buildRespContent(myProcotol);

        respPro.setContent(resp);
        ctx.writeAndFlush(respPro);
    }


    /**
     * 模拟和数据库交互
     * @param myProcotol
     * @return
     */
    private String buildRespContent(MyProcotol myProcotol) {
        return procotolService.buildRespContent(myProcotol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exception...");
        ctx.close();
    }
}
