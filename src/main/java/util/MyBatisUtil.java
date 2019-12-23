package util;

import com.alibaba.fastjson.JSON;
import entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static final String resource = "mybatis-config.xml";
    private static InputStream inputStream;
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.out.println("资源加载路径错误");
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 获取sql session
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭资源
     */
    public static void close(SqlSession sqlSession){
        try {
            inputStream.close();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
