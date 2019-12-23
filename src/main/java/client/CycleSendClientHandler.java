package client;

import constant.Constant;
import constant.MethodEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MyProcotol;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CycleSendHandler
 * @Description 周期发送包的的handler
 * @Author zhengxin
 * @Date 2019/12/23 18:50
 */
public class CycleSendClientHandler extends ChannelInboundHandlerAdapter {

    private ScheduledFuture channelFuture;

    ScheduledThreadPoolExecutor threadPoolTaskScheduler = new ScheduledThreadPoolExecutor(5);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("周期发送任务开始.....");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProcotol myProcotol = (MyProcotol) msg;

        //  收到握手请求后创建定时任务
        if (myProcotol.getCmd() == MethodEnum.SHAKE_HAND.getCode()) {
            channelFuture = threadPoolTaskScheduler.scheduleAtFixedRate(() -> {
                System.out.println("client send heart beat to server : --- > ");
            }, 0, 5000, TimeUnit.SECONDS);
        } else if (myProcotol.getCmd() == MethodEnum.CLIENT_CYCLE_SENG_MESSAGE.getCode()) {   //  对周期发送的包做处理
            System.out.println("receive cycle message from server.. ---- >" + myProcotol.toString());
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    /**
     * 构造周期函数基础包
     *
     * @return
     */
    private MyProcotol buildProcotol() {
        MyProcotol myProcotol = new MyProcotol();
        myProcotol.setHeader(Constant.HEADER);
        myProcotol.setVersion(Constant.VERSION);
        myProcotol.setCmd(MethodEnum.CLIENT_CYCLE_SENG_MESSAGE.getCode());
        myProcotol.setCode(Constant.CODE);
        return myProcotol;
    }

    /***
     * @desc 防止异常之后channelFuture不关闭造成的内存泄漏，
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (channelFuture != null) {
            channelFuture.cancel(true);
            // 促进GC
            channelFuture = null;
        }
        super.exceptionCaught(ctx, cause);
    }
}
