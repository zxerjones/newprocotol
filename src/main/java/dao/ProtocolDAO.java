package dao;

import entity.Student;
import protocol.MyProcotol;

import java.util.List;

/**
 * @ClassName ProtocolDAO
 * @Description 协议dao
 * @Author zhengxin
 * @Date 2019/12/19 21:24
 */
public interface ProtocolDAO {
    int insertlist(List<Student> list);

    List<MyProcotol> search();

    void insert(Student e);

    List<Student> selectList();
}
