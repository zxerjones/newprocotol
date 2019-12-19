import dao.ProtocolDAO;
import entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import protocol.MyProcotol;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName MyBatistest
 * @Description TODO
 * @Author zhengxin
 * @Date 2019年12月19日21:03:32
 */
public class MyBatistest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProtocolDAO protocolDAO = sqlSession.getMapper(ProtocolDAO.class);
        Student student = new Student();
        student.setAge(24);
        student.setName("郑鑫");
        student.setUid(UUID.randomUUID().toString().replaceAll("-",""));
        student.setDes("a coder");
        List<Student> list = Arrays.asList(student);
        System.out.println(protocolDAO.insertlist(list));
        sqlSession.commit();
        sqlSession.close();

    }
}
