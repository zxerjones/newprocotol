package server;

import constant.Constant;
import constant.EncrypEnum;
import constant.MethodEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MyProcotol;
import service.ProcotolService;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    private ProcotolService procotolService = new ProcotolService();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProcotol myProcotol = (MyProcotol) msg;
        if (myProcotol.getCmd() == MethodEnum.CLIENT_DATA_SAVE_IN_DB.getCode()) {
            System.out.println("server receive msg from client : " + myProcotol.toString());
            MyProcotol respPro = new MyProcotol();
            respPro.setHeader(myProcotol.getHeader());
            respPro.setVersion(myProcotol.getVersion());
            respPro.setCmd(myProcotol.getCmd());
            respPro.setCode(myProcotol.getCode());
            respPro.setEncryp(EncrypEnum.code);

            // 做具体的业务处理，保存数据或者向客户端推送数据
            String response = doWorkByCmd(myProcotol);

            respPro.setContentLength(response.getBytes().length);
            respPro.setContent(response);
            ctx.writeAndFlush(respPro);
        } else {
            // 透传
            ctx.fireChannelRead(msg);
        }


    }

    /**
     * 加密解密处理
     * // todo
     */



    /**
     * 根基cmd做具体的业务处理
     * @param myProcotol
     * @return
     */
    private String doWorkByCmd(MyProcotol myProcotol) {
        // 客户端发送数据保存到服务端
            return saveResult(myProcotol.getContent());
    }

    private String sendResult() {
        return procotolService.searchData();
    }


    /**
     * 模拟和数据库交互
     * @param content
     * @return
     */
    private String saveResult(String content) {
        return procotolService.buildResponse(content);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("server exception...");
        ctx.close();
        cause.printStackTrace();
    }
}
