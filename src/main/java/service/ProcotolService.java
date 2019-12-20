package service;

import com.alibaba.fastjson.JSON;
import dao.ProtocolDAO;
import entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.MyBatisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @ClassName ProcotolService
 * @Description 做业务处理
 * @Author zhengxin
 * @Date 2019/12/20 13:32
 */
public class ProcotolService {

    /**
     * 回收保存失败的数据
     */
    List<String> failData;

    /**
     * 保存sql session
     */
    private ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    public ProcotolService() {
        failData = new CopyOnWriteArrayList();
        threadLocal.set(MyBatisUtil.getSqlSession());
    }

    /**
     * 构建response
     * @param
     * @return
     */
    public String buildRespContent(String content) {
        List<Student> list = JSON.parseObject(content, List.class);
        SqlSession sqlSession = getSqlSession();
        ProtocolDAO protocolDAO = sqlSession.getMapper(ProtocolDAO.class);
        if (list.size() != 0) {
            protocolDAO.insertlist(list);
        }
        return null;
    }

    /**
     * 记录保存失败的数据
     */
    public String buildResponse(String content) {

        List<Student> list = JSON.parseArray(content, Student.class);

        ProtocolDAO protocolDAO = threadLocal.get().getMapper(ProtocolDAO.class);
        failData = list.stream().map(e -> {
            try {
                protocolDAO.insert(e);
                threadLocal.get().commit();
            } catch (Exception ex) {
                // 插入异常，保存用户uid
                return e.getUid();
            }
            return "";
        }).collect(Collectors.toList());
        // 关闭sqlsession
        MyBatisUtil.close(threadLocal.get());
        threadLocal.remove();
        return JSON.toJSONString(failData);
    }

    /**
     * 查找数据，发送给客户端
     */
    public String searchData() {
        return null;
    }


    /**
     *
     */
    private SqlSession getSqlSession() {
        String resource = "mybatis-config.xml";
        SqlSessionFactory sqlSessionFactory = null;
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sqlSessionFactory.openSession();
    }
}
