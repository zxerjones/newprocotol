package client;

import com.alibaba.fastjson.JSON;
import constant.Constant;
import constant.EncrypEnum;
import constant.MethodEnum;
import entity.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import protocol.MyProcotol;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 客户端发送请求，从服务器拉取数据 cmd=0x11
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyProcotol procotol = new MyProcotol();
        procotol.setHeader(Constant.HEADER);
        procotol.setVersion(Constant.VERSION);
        procotol.setCmd(MethodEnum.CLIENT_DATA_SAVE_IN_DB.getCode());
        procotol.setCode(Constant.CODE);
        procotol.setEncryp((byte) EncrypEnum.code);

        String content = JSON.toJSONString(buildContent());
        procotol.setContent(content);
        procotol.setContentLength(content.length());
        ctx.writeAndFlush(procotol);
    }

    /**
     * 模拟客户端发送content
     * @return
     */
    private List<Student> buildContent() {
        String baseName = "zxerjones";
        List<Student> list = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            Student student = new Student(getUid(), baseName + i, 24 + i, "coder " + i);
            list.add(student);
        }
        return list;
    }

    /**
     * 模拟生成uid
     * @return
     */
    private String getUid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
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